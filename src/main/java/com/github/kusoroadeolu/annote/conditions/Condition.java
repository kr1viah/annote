package com.github.kusoroadeolu.annote.conditions;

public enum Condition {
    OR(new Infix("||", 1, Arity.BINARY)),

    AND(new Infix("&&", 2, Arity.BINARY)),

    EQUALS(new Infix("==", 3, Arity.BINARY)),

    GREATER_THAN(new Infix(">", 4, Arity.BINARY)),
    LESS_THAN(new Infix("<", 4, Arity.BINARY)),
    GREATER_THAN_OR_EQUALS(new Infix(">=", 4, Arity.BINARY)),
    LESS_THAN_OR_EQUALS(new Infix("<=", 4, Arity.BINARY)),

    NOT(new Infix("!", 5, Arity.UNARY));

    private final Infix infix;

    Condition(Infix infix) {
        this.infix = infix;
    }

    public String getSymbol() {
        return infix.value();
    }

    public int getPrecedence() {
        return infix.precedence();
    }

    record Infix(String value, int precedence, Arity arity) {}

    enum Arity{
        UNARY,
        BINARY;
    }
}

