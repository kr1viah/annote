
```java
@Field("10", name = "age", type = "int")
@Field("some1", name = "name", type = "string")
class Class{
    
    @Var("10", name = "x", type = "int")
    @Var("x + 5", name = "y", type = "int")
    @Var("y + 1", name = "z", type = "int")
    @Return("z", type = "int")
    int method(){
        return 0;
    }

    @Var("10", name = "x", type = "int")
    @Var("x >= 12", name = "y", type = "bool")
    @Loop(condition = "y", body = "x = x + 1")
    @Var("x + 2", name = "x", type = "int") //Reassign variables, we check if this variable exists to see if we can reassign it before initializing it
    @Return("x", type = "int")
    int loop(){
        
    }
    
    @Var("x", val = "10", type = "int") //I think this is a bit cleaner, the name of the variable as the annotation value
    @Var("y", val = "20", type = "int")
    @Print("x + y")
    void printAdd(){
        
    }
    
    @Var("name", val = "Victor", type = "int")
    @Loop(condition = "true", body = @Print("name"))
    void printName(){}

    @If(condition = "x > 5", then = @Print("big"), else = @Print("small"))

}
```

