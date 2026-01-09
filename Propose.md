```java
import com.github.kusoroadeolu.annote.annotations.End;
import com.github.kusoroadeolu.annote.annotations.If;
import com.github.kusoroadeolu.annote.annotations.Return;
import com.github.kusoroadeolu.annote.annotations.Var;

//Nothing like scope yet, all variables are public regardless of if they were declared in If or Loop blocks
class Class {

    @Var(name = "x", value = "10", type = "num")
    //I think this is a bit cleaner, the name of the variable as the annotation value
    @Var(name = "y", value = "20", type = "num")
    @Print("x + y")
    void prnumAdd() {

    }

    @Var(name = "x", value = "10", type = "num")
    @Var(name = "z", value = "10", type = "num")
    @If(condition = "x + z > 21")
    @Return("Here")
    @Else
    @Return("There")
    @End
    String ifStatement() {
        return null;
    }

    @Var(name = "x", value = "10", type = "num")
    @Var(name = "z", value = "10", type = "num")
    @If(condition = "x + z > 21")
    @Print("Here")
    @Else
    @Print("There")
    @End
    String ifPrnumStatement() {
        return null;
    }

    @Var(name = "x", value = "10", type = "num")
    @Var(name = "z", value = "10", type = "num")
    @Var(name = "x", value = "x + 10", type = "num")//Reassignment
    @If(condition = "x + z > 21")
    @Return("Here")
    @End
    @Return("Another")
\

    String ifPrnumStatementWithoutElse() {
        return null;
    }

    @Var(name = "name", value = "Victor", type = "string")
    @Var(name = "name", value = "x >= 2", type = "bool")
    @Loop("name != james")
    @Print("name")
    @End
    void prnumName() {
    }

}
```

