package com.github.kusoroadeolu.annote.math;


import com.github.kusoroadeolu.annote.Utils;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

//So while the head of the stack has a less or equal precedence to the current operator, we pop them off the stack onto the list
public class MathParser {

    private final MathTokenizer tokenizer = new MathTokenizer();

    public double parse(String s) {
        List<Token> tokens = tokenizer.reform(s);
        Deque<Double> dq = new ArrayDeque<>();
        for (Token t : tokens){
            if (t.isNumber()) dq.push((double) t.o());
            else if (t.isOperator()){
                ArithmeticExpr e2 = new ArithmeticExpr.ArithmeticValue(dq.pop());
                ArithmeticExpr e1 = new ArithmeticExpr.ArithmeticValue(dq.pop());
                dq.push(calculate(e1, e2, (Operator) t.o()));
            }
        }

        return dq.pop();
    }

    // Note: brackets handled separately
     static double calculate(ArithmeticExpr e1, ArithmeticExpr e2, Operator operator){
        ArithmeticExpr e = switch (operator){
            case PLUS -> new ArithmeticExpr.Add(e1, e2);
            case MINUS -> new ArithmeticExpr.Subtract(e1, e2);
            case DIVISION -> new ArithmeticExpr.Divide(e1, e2);
            case MULTIPLY -> new ArithmeticExpr.Multiply(e1, e2);
            case MODULO -> new ArithmeticExpr.Modulo(e1, e2);
            case EXPONENTIAL -> new ArithmeticExpr.Exponential(e1, e2);
            default -> throw new IllegalArgumentException("??");
        };

        return Utils.asDouble(e.evaluate().value());
     }
}
