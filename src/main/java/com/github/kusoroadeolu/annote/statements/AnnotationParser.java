package com.github.kusoroadeolu.annote.statements;

import com.github.kusoroadeolu.annote.Runner;
import com.github.kusoroadeolu.annote.Type;
import com.github.kusoroadeolu.annote.annotations.*;
import com.github.kusoroadeolu.annote.annotations.containers.*;
import com.github.kusoroadeolu.annote.statements.Result.None;
import com.github.kusoroadeolu.annote.statements.Result.ReturnValue;
import com.github.kusoroadeolu.annote.statements.Statement.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

import static com.github.kusoroadeolu.annote.ExceptionSupplier.run;
import static com.github.kusoroadeolu.annote.Type.fromString;
import static java.util.Collections.sort;

public record AnnotationParser(Class<?> clazz) implements Runner {

    @Override
    public Result run(String methodName) {
        return this.read(methodName, new Scope(new HashMap<>(), null), true);
    }

    public Result read(String methodName, Scope rootScope, boolean addFields){
        Method method = run(() -> clazz.getMethod(methodName));
        Annotation[] annotations = flatten(method.getDeclaredAnnotations());
        Annotation[] fields = clazz.getDeclaredAnnotations();
        if (addFields){
            addFields(fields, rootScope);
        }

        var ls = parseAnnotations(annotations);
        Result rs = new None();
        for (Statement stmt : ls) {
            Result result = stmt.execute(rootScope);
            if (result instanceof ReturnValue rv) {
                return rv;
            }
        }

        return rs;
    }

    public static List<Statement> parseAnnotations(Annotation[] annotations) {
        List<Statement> program = new ArrayList<>();
        Deque<Block> blockStack = new ArrayDeque<>(); //Keep track of scope
        blockStack.push(new Block(program, null)); // Root block,

        for (Annotation a : annotations) {
            if (a instanceof If i) {
                handleIfStmt(i, blockStack);
            } else if (a instanceof Else) {
                handleElse(blockStack);
            } else if (a instanceof End) {
                blockStack.pop(); //Pop the previous if / while stmt
            } else if (a instanceof Var v) {
                handleVar(blockStack, v);
            } else if (a instanceof Print p) {
                blockStack.peek().add(new Statement.PrintStatement(p.value(), fromString(p.type())));
            } else if (a instanceof Loop l) {
                handleLoop(blockStack, l);
            } else if (a instanceof Return r) {
                handleReturn(blockStack, r);
            } else if (a instanceof Yeet y) {
                blockStack.peek().add(new YeetStatement(y.value()));
            }else if (a instanceof ReadLn r){
                blockStack.peek().add(new ReadLnStatement(r.prompt(), r.assignTo(), fromString(r.type())));
            }else if (a instanceof Concat c){
                blockStack.peek().add(new ConcatStatement(c.value(), c.assignTo()));
            }else if (a instanceof Call c){
                blockStack.peek().add(new CallStatement(c.methodName(), c.returnType(), c.assignTo(), c.clazz()));
            }
        }
        return program;
    }

    static Annotation[] flatten(Annotation[] arr) {
        List<OrderedAnnotation> ls = new ArrayList<>();
        for (Annotation a : arr) {
            if (a instanceof Vars vars) {
                for (Var v : vars.value()) {
                    ls.add(new OrderedAnnotation(v, v.order()));
                }
            } else if (a instanceof Prints prints) {
                for (Print p : prints.value()) {
                    ls.add(new OrderedAnnotation(p, p.order()));
                }
            } else if (a instanceof Ifs ifs) {
                for (If i : ifs.value()) {
                    ls.add(new OrderedAnnotation(i, i.order()));
                }
            } else if (a instanceof Ends ends) {
                for (End e : ends.value()) {
                    ls.add(new OrderedAnnotation(e, e.order()));
                }

            } else if (a instanceof Loops loops) {
                for (Loop l : loops.value()) {
                    ls.add(new OrderedAnnotation(l, l.order()));
                }
            } else if (a instanceof Returns returns) {
                for (Return r : returns.value()) {
                    ls.add(new OrderedAnnotation(r, r.order()));
                }
            } else if (a instanceof Elses elses) {
                for (Else e : elses.value()) {
                    ls.add(new OrderedAnnotation(e, e.order()));
                }
            } else if (a instanceof Yeets yeets) {
                for (Yeet e : yeets.value()) {
                    ls.add(new OrderedAnnotation(e, e.order()));
                }
            } else if (a instanceof ReadLns read) {
                for (ReadLn r : read.value()){
                    ls.add(new OrderedAnnotation(r, r.order()));
                }
            } else if (a instanceof Concats concats) {
                for (Concat c : concats.value()) {
                    ls.add(new OrderedAnnotation(c, c.order()));
                }
            } else if (a instanceof Calls calls) {
                for (Call c : calls.value()){
                    ls.add(new OrderedAnnotation(c, c.order()));
                }

            } else {
                ls.add(new OrderedAnnotation(a, getOrder(a)));
            }
        }

        sort(ls); //Sort the list
        return ls.stream().map(a -> a.a).toArray(Annotation[]::new);
    }

    static void addFields(Annotation[] fields, Scope rootScope){
        for (Annotation a : fields){
            if (a instanceof Fields fs) {
                for (Field f : fs.value()) rootScope.put(f.name(), new Variable(Type.fromString(f.type()), f.value()));
            }else if (a instanceof Field f){
                rootScope.put(f.name(), new Variable(Type.fromString(f.type()), f.value()));
            }
        }
    }

     static int getOrder(Annotation a) {
        try {
            var method = a.annotationType().getMethod("order");
            return (int) method.invoke(a);
        } catch (Exception e) {
            return Integer.MAX_VALUE;
        }
    }

    static void handleIfStmt(If i, Deque<Block> blockStack) {
        List<Statement> ifBlock = new ArrayList<>();
        List<Statement> elseBlock = new ArrayList<>();
        IfStatement ifStmt = new IfStatement(i.condition(), ifBlock, elseBlock);
        blockStack.peek().add(ifStmt); // Add to current block
        blockStack.push(new Block(ifStmt.ifBlock(), ifStmt));
    }

    static void handleElse(Deque<Block> blockStack) {
        Block ifBlock = blockStack.pop(); //Remove the if statements and replace with else statements
        IfStatement ifStmt = (IfStatement) ifBlock.parentStatement();
        blockStack.push(new Block(ifStmt.elseBlock(), ifStmt));
    }

    static void handleVar(Deque<Block> blockStack, Var v) {
        Statement varDecl = new Statement.VarDeclaration(v.name(), fromString(v.type()), v.value());
        blockStack.peek().add(varDecl);
    }

    static void handleLoop(Deque<Block> blockStack, Loop l) {
        List<Statement> body = new ArrayList<>();
        LoopStatement loopStmt = new LoopStatement(l.condition(), body);
        blockStack.peek().add(loopStmt);
        blockStack.push(new Block(body, loopStmt));
    }

    static void handleReturn(Deque<Block> blockStack, Return r) {
        ReturnStatement statement = new ReturnStatement(r.value(), fromString(r.type()));
        blockStack.peek().add(statement);
    }

    record OrderedAnnotation(Annotation a, Integer i) implements Comparable<OrderedAnnotation> {
        @Override
        public int compareTo(OrderedAnnotation o) {
            return i.compareTo(o.i);
        }
    }

}
