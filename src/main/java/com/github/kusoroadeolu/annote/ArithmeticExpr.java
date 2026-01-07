package com.github.kusoroadeolu.annote;

import static com.github.kusoroadeolu.annote.Utils.*;

public interface ArithmeticExpr extends Expression{
    ArithmeticValue evaluate();


    record ArithmeticValue(Object value) implements ArithmeticExpr, Value{
        public ArithmeticValue evaluate() {
            return this;
        }
    }


    record Add(ArithmeticExpr e1, ArithmeticExpr e2) implements ArithmeticExpr {
        @Override
        public ArithmeticValue evaluate() {
            Object o1 = e1.evaluate().value();
            Object o2 = e2.evaluate().value();
            if (isStringInstance(o1) || isStringInstance(o2)){
                String s1 = String.valueOf(o1);
                String s2 = String.valueOf(o2);
                return new ArithmeticValue(s1 + s2);
            }else if (isDoubleInstance(o1) || isDoubleInstance(o2)){
                double d1 = asDouble(o1);
                double d2 = asDouble(o2);
                return new ArithmeticValue(d1 + d2); //Just cast both to double
            }else { //Both should be integers, since you cant add booleans
                int i1 = asInt(o1);
                int i2 = asInt(o2);
                return new ArithmeticValue(i1 + i2);
            }

        }
    }


    record Subtract(ArithmeticExpr e1, ArithmeticExpr e2) implements ArithmeticExpr {
        @Override
        public ArithmeticValue evaluate() {
            Object o1 = e1.evaluate().value();
            Object o2 = e2.evaluate().value();
            ensureNotString(o1, o2);
            ensureNotBoolean(o1, o2);

            if (isDoubleInstance(o1) || isDoubleInstance(o2)){
                double d1 = asDouble(o1);
                double d2 = asDouble(o2);
                return new ArithmeticValue(d1 - d2); //Just cast both to double
            }else { //Both should be integers, since you cant add booleans
                int i1 = asInt(o1);
                int i2 = asInt(o2);
                return new ArithmeticValue(i1 - i2);
            }

        }
    }

    record Divide(ArithmeticExpr e1, ArithmeticExpr e2) implements ArithmeticExpr {
        @Override
        public ArithmeticValue evaluate() {
            Object o1 = e1.evaluate().value();
            Object o2 = e2.evaluate().value();
            ensureNotString(o1, o2);
            ensureNotBoolean(o1, o2);
            if (isDoubleInstance(o1) || isDoubleInstance(o2)){
                double d1 = asDouble(o1);
                double d2 = asDouble(o2);
                return new ArithmeticValue(d1 / d2); //Just cast both to double
            }else { //Both should be integers, since you cant add booleans
                int i1 = asInt(o1);
                int i2 = asInt(o2);
                return new ArithmeticValue(asDouble(i1/i2));
            }

        }
    }

    record Multiply(ArithmeticExpr e1, ArithmeticExpr e2) implements ArithmeticExpr {
        @Override
        public ArithmeticValue evaluate() {
            Object o1 = e1.evaluate().value();
            Object o2 = e2.evaluate().value();
            ensureNotString(o1, o2);
            ensureNotBoolean(o1, o2);

            if (isDoubleInstance(o1) || isDoubleInstance(o2)){
                double d1 = asDouble(o1);
                double d2 = asDouble(o2);
                return new ArithmeticValue(d1 * d2); //Just cast both to double
            }else { //Both should be integers, since you cant add booleans
                int i1 = asInt(o1);
                int i2 = asInt(o2);
                return new ArithmeticValue(i1 * i2);
            }
        }
    }

    record Modulo(ArithmeticExpr e1, ArithmeticExpr e2) implements ArithmeticExpr {
        public ArithmeticValue evaluate() {
            Object o1 = e1.evaluate().value();
            Object o2 = e2.evaluate().value();
            ensureNotString(o1, o2);
            ensureNotBoolean(o1, o2);

            if (isDoubleInstance(o1) || isDoubleInstance(o2)){
                double d1 = asDouble(o1);
                double d2 = asDouble(o2);
                return new ArithmeticValue(d1 % d2); //Just cast both to double
            }else { //Both should be integers, since you cant add booleans
                int i1 = asInt(o1);
                int i2 = asInt(o2);
                return new ArithmeticValue(i1 % i2);
            }

        }
    }
}
