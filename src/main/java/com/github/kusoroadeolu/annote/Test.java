package com.github.kusoroadeolu.annote;

import com.github.kusoroadeolu.annote.annotations.Print;
import com.github.kusoroadeolu.annote.annotations.Var;

public class Test {

    @Var(name = "var1", value = "2", type = "num")
    @Var(name = "var2", value = "3", type = "num")
    @Var(name = "var3", value = "var1 + var2", type = "num")
    @Print(value = "var3", type = "num")
    public void smth(){}
}
