package com.github.kusoroadeolu.annote;

import static java.lang.IO.println;

public class Utils {

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

    public static int asInt(Object o){
        return ((Number)o).intValue();
    }

    public static String asString(Object o){
        return String.valueOf(o);
    }

    public static boolean asBoolean(Object o){
        return Boolean.parseBoolean(asString(o));
    }


}
