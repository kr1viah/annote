package com.github.kusoroadeolu.annote.math;

import com.github.kusoroadeolu.annote.Utils;
import com.github.kusoroadeolu.annote.tokenizer.Operator;
import com.github.kusoroadeolu.annote.tokenizer.SymbolTokenizer;
import com.github.kusoroadeolu.annote.tokenizer.Token;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class MathParser {

    private final SymbolTokenizer tokenizer = new SymbolTokenizer();

    public double parse(String s) {
        List<Token> tokens = tokenizer.reform(s);
        Deque<Double> dq = new ArrayDeque<>();
        for (Token t : tokens){
            if (t.isNumber()) dq.push((double) t.o());
            else if (t.isOperator()){
                ArithmeticExpr e2 = new ArithmeticExpr.ArithmeticValue(dq.pop());
                ArithmeticExpr e1 = new ArithmeticExpr.ArithmeticValue(dq.pop());
                dq.push(evaluate(e1, e2, (Operator) t.o()));
            }
        }

        return dq.pop();
    }

    // Note: brackets handled separately
    static double evaluate(ArithmeticExpr e1, ArithmeticExpr e2, Operator operator){
        var e = Utils.eval(e1, e2, operator);
        return Utils.asDouble(e.evaluate().value());
    }

}
