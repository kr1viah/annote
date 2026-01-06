package com.github.kusoroadeolu.annote;

public enum Type {
    INT("int"),
    DOUBLE("double"),
    BOOLEAN("bool"),
    STRING("string");

    private final String value;

    Type(String value) {
        this.value = value;
    }

    static Type fromString(String type){
        type = type.toLowerCase();
        return switch (type){
            case "int" -> Type.INT;
            case "double" -> Type.DOUBLE;
            case "bool" -> Type.BOOLEAN;
            case "string" -> Type.STRING;
            default -> throw new IllegalArgumentException();
        };
    }

    public String toString(){
        return value;
    }
}
