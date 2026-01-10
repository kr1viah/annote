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

    @Var(name = "result", value = "1", type = "num", order = 1)
    @Loop(condition = "!(result==0)", order = 2)
    @ReadLn(assignTo = "input", prompt = "calc> (or 0 to exit): ", type = "string", order = 3)
    @Var(name = "result", value = "input", type = "num", order = 4)
    @If(condition = "!(result==0)", order = 5)
    @Concat(assignTo = "output", value = {"= ", "result"}, order = 6)
    @Print(value = "output", type = "string", order = 7)
    @End(order = 8)
    @End(order = 9)
    @Print(value = "Goodbye!", order = 10)
    public void repl() {}




}
