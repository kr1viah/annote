package com.github.kusoroadeolu.annote;

import com.github.kusoroadeolu.annote.annotations.Var;
import com.github.kusoroadeolu.annote.math.MathParser;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static com.github.kusoroadeolu.annote.ExceptionSupplier.run;

public class MethodAnnotationReader {
    private final MathParser parser = new MathParser();
    private final Class<?> clazz;

    public MethodAnnotationReader(Class<?> clazz) {
        this.clazz = clazz;
    }

    public void readAnnotations(String methodName){
        Method method = run(() -> clazz.getMethod(methodName));;
        Annotation[] annotations = method.getDeclaredAnnotations();
        Map<String, Variable> publicVariables = new HashMap<>(); //A list of the public variables
        for (Annotation a : annotations){
            if (a instanceof Var v){
                String val = v.value(), type = v.type(), name = v.name();
                if (val == null) continue;
                Type t = Type.fromString(type);
                if (t == Type.NUMBER){
                    double num = 0d;
                    if (!val.isBlank()){
                        String rebuilt = this.rebuildString(val, publicVariables);
                        num = parser.parse(rebuilt);
                    }
                    publicVariables.put(name, new Variable(t, num));
                }else if (t == Type.BOOLEAN){
                    String rebuilt = this.rebuildString(val, publicVariables);


                }


            }
        }
    }



    String rebuildString(String original, Map<String, Variable> map){
        original = original.replaceAll("\\s++", "");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < original.length(); i++){
            char c = original.charAt(i);
            Object val = map.get(String.valueOf(c));
            if (val != null) builder.append(val);
            else builder.append(c);
        }

        return builder.toString();
    }

    record Variable(Type type, Object o){ }
}
