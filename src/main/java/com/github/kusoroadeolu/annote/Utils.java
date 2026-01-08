package com.github.kusoroadeolu.annote;

import com.github.kusoroadeolu.annote.math.ArithmeticExpr;
import com.github.kusoroadeolu.annote.tokenizer.Operator;

import java.util.Set;

import static java.lang.IO.println;

public class Utils {

    private final static Set<String> INVALID_CHARS = Set.of("|", "&", "=");

    public static boolean isDoubleInstance(Object o){
        return o instanceof Double;
    }

    public static boolean isStringInstance(Object o){
        return o instanceof String;
    }

    public static boolean isBoolInstance(Object o){
        return o instanceof Boolean;
    }


    public static void ensureNotString(Object o1, Object o2){
        if (isStringInstance(o1) || isStringInstance(o2)) throw new IllegalArgumentException("string cannot perform this op");
    }

    public static void ensureNotBoolean(Object o1, Object o2){
        if (isBoolInstance(o1) || isBoolInstance(o2)) throw new IllegalArgumentException("string cannot perform this op");
    }

    public static void print(Object o){
        println(o);
    }

    public static double asDouble(Object o){
        return ((Number)o).doubleValue();
    }

    public static boolean isInvalidChar(char c){
        return INVALID_CHARS.contains(String.valueOf(c));
    }

    public static int asInt(Object o){
        return ((Number)o).intValue();
    }

    public static String asString(Object o){
        return String.valueOf(o);
    }

    public static boolean asBoolean(Object o){
        return Boolean.parseBoolean(asString(o));
    }


    public static ArithmeticExpr eval(ArithmeticExpr e1, ArithmeticExpr e2, Operator operator){
        return switch (operator){
            case PLUS -> new ArithmeticExpr.Add(e1, e2);
            case MINUS -> new ArithmeticExpr.Subtract(e1, e2);
            case DIVISION -> new ArithmeticExpr.Divide(e1, e2);
            case MULTIPLY -> new ArithmeticExpr.Multiply(e1, e2);
            case MODULO -> new ArithmeticExpr.Modulo(e1, e2);
            case EXPONENTIAL -> new ArithmeticExpr.Exponential(e1, e2);
            default -> throw new IllegalArgumentException("??");
        };
    }

}
