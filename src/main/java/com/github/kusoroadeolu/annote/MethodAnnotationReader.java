package com.github.kusoroadeolu.annote;

import com.github.kusoroadeolu.annote.annotations.Var;
import com.github.kusoroadeolu.annote.conditions.ConditionParser;
import com.github.kusoroadeolu.annote.math.MathParser;
import com.github.kusoroadeolu.annote.tokenizer.Condition;
import com.github.kusoroadeolu.annote.tokenizer.Operator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static com.github.kusoroadeolu.annote.ExceptionSupplier.run;

public class MethodAnnotationReader {
    private final MathParser mathParser = new MathParser();
    private final ConditionParser conditionParser = new ConditionParser();
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
                    double num = 0;
                    if (!val.isBlank()){
                        String rebuilt = this.insertVariables(val, publicVariables);
                        num = mathParser.parse(rebuilt);
                    }
                    publicVariables.put(name, new Variable(t, num));
                }else if (t == Type.BOOLEAN){
                    String rebuilt = this.insertVariables(val, publicVariables);
                    boolean b = conditionParser.parse(rebuilt);
                    publicVariables.put(name, new Variable(t, b));
                }else if (t == Type.STRING){
                    publicVariables.put(name, new Variable(t, v.value()));
                }


            }
        }
    }



    String insertVariables(String original, Map<String, Variable> map){
        original = original.replaceAll("\\s++", "");
        StringBuilder varBuilder = new StringBuilder();
        StringBuilder mainBuilder = new StringBuilder();
        for (int i = 0; i < original.length(); i++){
            char c = original.charAt(i);
            if (Condition.isCondition(c) || Operator.isOperator(c) || Utils.isInvalidChar(c)){
                if (!varBuilder.isEmpty()){
                    IO.println("Var value: " + varBuilder);
                    Variable v = map.get(varBuilder.toString());
                    if (v != null) mainBuilder.append(v.o());
                    else throw new IllegalArgumentException("Variable: '%s' not found".formatted(varBuilder)); //This is kinda hacky, might come back to this

                    varBuilder.setLength(0);
                }

                mainBuilder.append(c);
                continue;
            }
            varBuilder.append(c);
        }

        if (!varBuilder.isEmpty()) {
            Variable v = map.get(varBuilder.toString());
            if (v != null) mainBuilder.append(v.o());
            else throw new IllegalArgumentException("Variable: '%s' not found".formatted(varBuilder));

        }

        return mainBuilder.toString();
    }

    public record Variable(Type type, Object o){ }
}
