package com.github.kusoroadeolu.annote;

import com.github.kusoroadeolu.annote.annotations.*;
import com.github.kusoroadeolu.annote.statements.Block;
import com.github.kusoroadeolu.annote.statements.Result;
import com.github.kusoroadeolu.annote.statements.Scope;
import com.github.kusoroadeolu.annote.statements.Statement;
import com.github.kusoroadeolu.annote.statements.Statement.IfStatement;
import com.github.kusoroadeolu.annote.statements.Statement.LoopStatement;
import com.github.kusoroadeolu.annote.statements.Statement.ReturnStatement;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

import static com.github.kusoroadeolu.annote.ExceptionSupplier.run;
import static com.github.kusoroadeolu.annote.Type.fromString;

public class AnnotationParser {
    private final Class<?> clazz;


    public AnnotationParser(Class<?> clazz) {
        this.clazz = clazz;
    }

    public Result read(String methodName){
        Method method = run(() -> clazz.getMethod(methodName));
        Annotation[] annotations = method.getDeclaredAnnotations();
        var ls = parseAnnotations(annotations);
        Scope rootScope = new Scope(new HashMap<>(), null);

        for (Statement stmt : ls) {
            Result result = stmt.execute(rootScope);
            if (result instanceof Result.ReturnValue rv) {
                return rv; // or handle the return
            }
        }

        return new Result.None();
    }

    public static List<Statement> parseAnnotations(Annotation[] annotations) {
        List<Statement> program = new ArrayList<>();
        Deque<Block> blockStack = new ArrayDeque<>(); //Keep track of scope
        blockStack.push(new Block(program, null)); // Root stmt

        for (Annotation a : annotations) {
            if (a instanceof Ifs ifs) {
                for (If i : ifs.value()){
                    handleIfStmt(i, blockStack); // If and Loop statements create a new block (list of statements) during parsing.
                    // We push that block onto the stack and add statements to it until we see @End.
                    // At execution time, these blocks also get their own variable scope.
                }
            }else if(a instanceof If i){
                handleIfStmt(i, blockStack);
            }
            else if (a instanceof Elses elses) {
                for (Else e: elses.value()){
                    handleElse(blockStack);
                }
            } else if (a instanceof Else e) {
                handleElse(blockStack);
            } else if (a instanceof Ends ends) {
                for (End e: ends.value()){
                    blockStack.pop();
                }
            } else if (a instanceof End e) {
                blockStack.pop(); //Pop the previous if / while stmt
            } else if (a instanceof Vars vars) {
                for (Var v : vars.value()){
                    handleVar(blockStack, v);
                }
            } else if (a instanceof Var v) {
                handleVar(blockStack, v);
            } else if (a instanceof Prints prints) {
                for (Print p: prints.value()){
                    blockStack.peek().add(new Statement.PrintStatement(p.value(), fromString(p.type())));
                }
            }else if (a instanceof Print p){
                blockStack.peek().add(new Statement.PrintStatement(p.value(), fromString(p.type())));
            }
            else if (a instanceof Loops loops) {
                for (Loop l : loops.value()){
                    handleLoop(blockStack, l);
                }
            }else if(a instanceof Loop l){
                handleLoop(blockStack, l);
            }
            else if (a instanceof Returns returns){
                for (Return r : returns.value()){
                   handleReturn(blockStack, r);
                }
            }else if(a instanceof Return r){
                handleReturn(blockStack, r);
            }
        }

        return program;
    }

    static void handleIfStmt(If i,Deque<Block> blockStack){
        List<Statement> thenBlock = new ArrayList<>();
        List<Statement> elseBlock = new ArrayList<>();
        IfStatement ifStmt = new IfStatement(i.value(), thenBlock, elseBlock);
        blockStack.peek().add(ifStmt); // Add to current block
        blockStack.push(new Block(thenBlock, ifStmt));
    }

    static void handleElse(Deque<Block> blockStack){
        Block thenBuilder = blockStack.pop();
        IfStatement ifStmt = (IfStatement) thenBuilder.parentStatement();
        blockStack.push(new Block(ifStmt.elseBlock(), ifStmt));
    }

    static void handleVar(Deque<Block> blockStack, Var v){
        Statement varDecl = new Statement.VarDeclaration(v.name(), fromString(v.type()), v.value());
        blockStack.peek().add(varDecl);
    }

    static void handleLoop(Deque<Block> blockStack, Loop l){
        List<Statement> body = new ArrayList<>();
        LoopStatement loopStmt = new LoopStatement(l.value(), body);
        blockStack.peek().add(loopStmt);
        blockStack.push(new Block(body, loopStmt));
    }

    static void handleReturn(Deque<Block> blockStack, Return r){
        ReturnStatement statement = new ReturnStatement(r.value(), Type.fromString(r.type()));
        blockStack.peek().add(statement);
    }
}
