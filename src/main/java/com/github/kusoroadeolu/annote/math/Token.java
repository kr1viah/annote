package com.github.kusoroadeolu.annote.math;

record Token(Object o) {
    public boolean isNumber(){
        return o instanceof Number;
    }

    public boolean isOperator(){
        return o instanceof Operator;
    }
}
