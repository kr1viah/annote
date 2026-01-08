package com.github.kusoroadeolu.annote.tokenizer;

import java.util.Map;
import java.util.Set;

public enum Operator implements Symbol{
    PLUS(new Infix("+", 1)),
    MINUS(new Infix("-", 1)),
    MULTIPLY(new Infix("*", 2)),
    DIVISION(new Infix("/", 2)),
    MODULO(new Infix("%", 2)),
    EXPONENTIAL(new Infix("^", 3)),
    LEFT_BRACKET(new Infix("(", 4)),
    RIGHT_BRACKET(new Infix(")", 4));


    private final Infix infix;
    Operator(Infix infix) {
        this.infix = infix;
    }

    boolean hasLessOrEqualPrecedence(Operator o){
        return this.infix.precedence() <= o.infix.precedence() && !o.isBracket(); //Ensure this isnt a bracket too so we dont add it to our outputs
    }

    public static Operator fromChar(char c){
        return switch (c){
            case '+' -> Operator.PLUS;
            case '-' -> Operator.MINUS;
            case '*' -> Operator.MULTIPLY;
            case '/' -> Operator.DIVISION;
            case '%' -> Operator.MODULO;
            case '^' -> Operator.EXPONENTIAL;
            case '(' -> Operator.LEFT_BRACKET;
            case ')' -> Operator.RIGHT_BRACKET;
            default -> throw new IllegalArgumentException("Not an symbol. Might be a condition");
        };
    }

    @Override
    public String toString() {
        return String.valueOf(infix);
    }


    public Infix infix() {
        return this.infix;
    }
}