package com.github.kusoroadeolu.annote;

import static java.lang.IO.println;

public class Utils {

    static boolean isDoubleInstance(Object o){
        return o instanceof Double;
    }

    static boolean isStringInstance(Object o){
        return o instanceof String;
    }

    static boolean isBoolInstance(Object o){
        return o instanceof Boolean;
    }


    static void ensureNotString(Object o1, Object o2){
        if (isStringInstance(o1) || isStringInstance(o2)) throw new IllegalArgumentException("string cannot perform this op");
    }

    static void ensureNotBoolean(Object o1, Object o2){
        if (isBoolInstance(o1) || isBoolInstance(o2)) throw new IllegalArgumentException("string cannot perform this op");
    }

    static void print(Object o){
        println(o);
    }

    static double asDouble(Object o){
        return ((Number)o).doubleValue();
    }

    static int asInt(Object o){
        return ((Number)o).intValue();
    }

    static String asString(Object o){
        return String.valueOf(o);
    }

    static boolean asBoolean(Object o){
        return Boolean.parseBoolean(asString(o));
    }

}
