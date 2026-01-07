package com.github.kusoroadeolu.annote;

import com.github.kusoroadeolu.annote.annotations.Var;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.github.kusoroadeolu.annote.ExceptionSupplier.run;
import static com.github.kusoroadeolu.annote.Type.fromString;
import static com.github.kusoroadeolu.annote.Utils.*;

public record MethodReader(Class<?> clazz) {
    public void readAnnotations(String methodName){
        Method method = run(() -> clazz.getMethod(methodName));;
        Annotation[] annotations = method.getDeclaredAnnotations();
        Map<String, Variable> symbolTable = new LinkedHashMap<>();
        for (Annotation a : annotations){
            if (a instanceof Var v){
                symbolTable.put(v.name(), new Variable(fromString(v.type()), v.value()));

            }
        }
    }

    String rebuildString(String original, Map<String, Variable> map){
        original = original.replaceAll("\\s++", "");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < original.length(); i++){
            char c = original.charAt(i);
            Object val = map.get(String.valueOf(c));
            if (val != null)builder.append(val);
            else builder.append(c);
        }

        return builder.toString();
    }

    record Variable(Type type, Object o){
         <E>E cast(){
            return (E) switch (type) {
                case INT -> asInt(o);
                case DOUBLE -> asDouble(o);
                case BOOLEAN -> asBoolean(o);
                case STRING -> asString(o);
            };
         }
    }
}
