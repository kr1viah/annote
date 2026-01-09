package com.github.kusoroadeolu.annote;

import com.github.kusoroadeolu.annote.annotations.*;

public class Test {

    @ReadLn(name = "var1", prompt = "Enter a number: ", type = "num", order = 1)
    @ReadLn(name = "var2", prompt = "Enter another number: ", type = "num", order = 2)
    @Var(name = "var3", value = "var1 + var2", type = "num", order = 3)
    @Concat(name = "res", value = {"Result: ", "var3"}, order = 4)
    @Print(value = "res", type = "string", order = 5)
    public void smth(){}
}
