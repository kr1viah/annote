package com.github.kusoroadeolu.annote;

import com.github.kusoroadeolu.annote.annotations.*;

public class Test {

    @Var(name = "var1", value = "2", type = "num", order = 1)
    @Var(name = "var2", value = "3", type = "num", order = 2)
    @Var(name = "var3", value = "var1 + var2", type = "num", order = 3)
    @If(value = "var3 == 5", order = 4)
        @Print(value = "hello", order = 5)
            @If(value = "var3 == 5", order = 6)
                @Print(value = "heyy", order = 7)
            @End(order = 8)
        @End(order = 9)
    public void smth(){}
}
