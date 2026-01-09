package com.github.kusoroadeolu.annote;

import com.github.kusoroadeolu.annote.annotations.*;

public class Test {

    @Var(name = "var1", value = "2", type = "num", order = 1)
    @Var(name = "var2", value = "3", type = "num", order = 2)
    @Var(name = "var3", value = "var1 + var2", type = "num", order = 3)
    @If(value = "var3 == 4", order = 4)
        @Print(value = "hello", order = 5)
    @Else(order = 6)
        @Yeet(value = "Bozo", order = 7)
    public void smth(){}
}
