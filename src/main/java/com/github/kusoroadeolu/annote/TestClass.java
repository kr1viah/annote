package com.github.kusoroadeolu.annote;

import com.github.kusoroadeolu.annote.annotations.*;

@Field(name = "i", value = "1", type = "num")
@Field(name = "j", value = "1", type = "num")
public class TestClass {

    @Var(name = "i", value = "1", type = "num", order = 1)
    @Loop(condition = "i <= 100", order = 2)
        @If(condition = "i % 15 == 0", order = 3)
            @Print(value = "FizzBuzz", order = 4)
        @Else(order = 5)
            @If(condition = "i % 3 == 0", order = 6)
                @Print(value = "Fizz", order = 7)
            @Else(order = 8)
                @If(condition = "i % 5 == 0", order = 9)
                    @Print(value = "Buzz", order = 10)
                @Else(order = 11)
                    @Print(value = "i", type = "num", order = 12)
                @End(order = 13)
            @End(order = 14)
        @End(order = 15)
        @Var(name = "i", value = "i + 1", type = "num", order = 16)
    @End(order = 17)
    public void fizzbuzz() {}

    @Var(name = "i", value = "1", type = "num", order = 1)
    @Var(name = "i", value = "i + 1", type = "num", order = 2)
    @Print(value = "i", type = "num", order = 3)
    public void print(){

    }

    @Call(methodName = "print", clazz = TestClass.class, order = 0)
    public void call(){

    }

    @Print(value = "i + j", type = "num", order = 1)
    public void tryFields(){

    }
}
