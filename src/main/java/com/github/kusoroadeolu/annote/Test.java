package com.github.kusoroadeolu.annote;

import com.github.kusoroadeolu.annote.annotations.*;

public class Test {

    @ReadLn(assignTo = "var1", prompt = "Enter a number: ", type = "num", order = 1)
    @ReadLn(assignTo = "var2", prompt = "Enter another number: ", type = "num", order = 2)
    @Var(name = "var3", value = "var1 + var2", type = "num", order = 3)
    @Concat(assignTo = "res", value = {"Result: ", "var3"}, order = 4)
    @Return(value = "var3", type = "string", order = 5)
    public void smth(){}

    @Call(methodName = "smth", returnType = "string", clazz = Test.class, order = 1)
    public void call(){

    }
}
