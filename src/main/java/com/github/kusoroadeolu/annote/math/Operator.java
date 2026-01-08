package com.github.kusoroadeolu.annote.math;

enum Operator {
    PLUS(new Infix('+', 1)),
    MINUS(new Infix('-', 1)),
    MULTIPLY(new Infix('*', 2)),
    DIVISION(new Infix('/', 2)),
    MODULO(new Infix('%', 2)),
    EXPONENTIAL(new Infix('^', 3)),
    LEFT_BRACKET(new Infix('(', 4)),
    RIGHT_BRACKET(new Infix(')', 4));



    private final Infix infix;

    Operator(Infix infix) {
        this.infix = infix;
    }

    boolean isLeftBracket(){
        return this.infix.operator() == '(';
    }

    boolean isRightBracket(){
        return this.infix.operator() == ')';
    }

    boolean isBracket(){
        return isLeftBracket() || isRightBracket();
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
            default -> throw new IllegalArgumentException("???");
        };
    }


    @Override
    public String toString() {
        return String.valueOf(infix);
    }

    record Infix(char operator, int precedence){

    }
}