package com.github.kusoroadeolu.annote;

import static com.github.kusoroadeolu.annote.Utils.*;

public interface ConditionExpr extends Expression{
    Value evaluate();

    record BoolValue(Object value) implements ConditionExpr, Value{
        @Override
        public BoolValue evaluate() {
            return this;
        }
    }

    record Equals(Expression e1, Expression e2) implements ConditionExpr{
        @Override
        public BoolValue evaluate() {
            Object o1 = e1.evaluate().value();
            Object o2 = e2.evaluate().value();
            return new BoolValue(o1.equals(o2));
        }
    }

    record GreaterThan(Expression e1, Expression e2) implements ConditionExpr {
        @Override
        public BoolValue evaluate() {
            Object o1 = e1.evaluate().value();
            Object o2 = e2.evaluate().value();
            ensureNotBoolean(o1, o2);
            ensureNotString(o1, o2);
            return new BoolValue(toDouble(o1) > toDouble(o2));
        }
    }

    record LessThan(Expression e1, Expression e2) implements ConditionExpr {
        @Override
        public BoolValue evaluate() {
            Object o1 = e1.evaluate().value();
            Object o2 = e2.evaluate().value();
            ensureNotBoolean(o1, o2);
            ensureNotString(o1, o2);
            return new BoolValue(toDouble(o1) < toDouble(o2));
        }
    }



    record GreaterThanOrEquals(Expression e1, Expression e2) implements ConditionExpr {
        @Override
        public BoolValue evaluate() {
            Object o1 = e1.evaluate().value();
            Object o2 = e2.evaluate().value();
            ensureNotBoolean(o1, o2);
            ensureNotString(o1, o2);
            return new BoolValue(toDouble(o1) >= toDouble(o2));
        }
    }

    record LessThanOrEquals(Expression e1, Expression e2) implements ConditionExpr {
        @Override
        public BoolValue evaluate() {
            Object o1 = e1.evaluate().value();
            Object o2 = e2.evaluate().value();
            ensureNotBoolean(o1, o2);
            ensureNotString(o1, o2);
            return new BoolValue(toDouble(o1)  <= toDouble(o2));
        }
    }

    record Not(BoolValue result) implements ConditionExpr {
        @Override
        public BoolValue evaluate() {
            return new BoolValue(!(boolean)result.value());
        }
    }

    record And(BoolValue r1, BoolValue r2) implements ConditionExpr{
        @Override
        public BoolValue evaluate() {
            return new BoolValue((boolean)r1.value && (boolean)r2.value);
        }
    }

    record Or(BoolValue r1, BoolValue r2) implements ConditionExpr{
        @Override
        public BoolValue evaluate() {
            return new BoolValue((boolean)r1.value || (boolean)r2.value);
        }
    }
}