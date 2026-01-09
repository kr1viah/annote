package com.github.kusoroadeolu.annote.statements;

import com.github.kusoroadeolu.annote.Expression;
import com.github.kusoroadeolu.annote.Type;
import com.github.kusoroadeolu.annote.Utils;
import com.github.kusoroadeolu.annote.conditions.ConditionParser;
import com.github.kusoroadeolu.annote.math.ArithmeticExpr;
import com.github.kusoroadeolu.annote.math.MathParser;
import com.github.kusoroadeolu.annote.statements.Result.None;
import com.github.kusoroadeolu.annote.statements.Result.ReturnValue;

import java.util.List;

import static com.github.kusoroadeolu.annote.Type.*;
import static com.github.kusoroadeolu.annote.Utils.asBoolean;
import static com.github.kusoroadeolu.annote.Utils.print;

//Strings are immutable, cannot be concat, how to
public interface Statement {
   Result execute(Scope scope);

   default Expression expr(String string, Scope scope  ,Type type){
       String rebuilt = Utils.insertVariables(string, scope.variables());
       return switch (type){
           case NUMBER -> MathParser.parse(rebuilt);
           case BOOLEAN -> ConditionParser.parse(rebuilt);
           case STRING -> new ArithmeticExpr.ArithmeticValue(string);
       };
   }
     record VarDeclaration(String name, Type type, String string) implements Statement{
        @Override
        public Result execute(Scope scope) {
            Expression expr = expr(string, scope, type);
            scope.variables().put(name, new Variable(type, expr.evaluate().value()));
            return new None();
        }
    }

    record IfStatement(String string, List<Statement> ifBlock, List<Statement> elseBlock) implements Statement {
        @Override
        public Result execute(Scope scope) {
            Expression condition = expr(string, scope, BOOLEAN);
            List<Statement> block = asBoolean(condition.evaluate().value()) ? ifBlock : elseBlock;
            Scope inner = scope.newScope();
            for (Statement s : block) {
                Result result = s.execute(inner);
                if (result instanceof ReturnValue) {
                    return result;
                }
            }

            return new None();
        }
    }
    record PrintStatement(String string, Type type) implements Statement {
        @Override
        public Result execute(Scope scope) {
            Expression expression = expr(string, scope, type);
            print(expression.evaluate().value());
            return new None();
        }
    }
    record LoopStatement(String string, List<Statement> body) implements Statement {
        @Override
        public Result execute(Scope scope) {
            Scope inner = scope.newScope();
            Expression condition = expr(string, scope, BOOLEAN);
            while (asBoolean(condition.evaluate().value())){
                for (Statement s : body) {
                    Result result = s.execute(inner);
                    if (result instanceof ReturnValue) {
                        return result;
                    }
                }
            }

            return new None();
        }
    }

    record ReturnStatement(String string, Type type) implements Statement{
        @Override
        public Result execute(Scope scope) {
            Expression expression = expr(string, scope, type);
            return new ReturnValue(expression.evaluate().value());
        }
    }
}
