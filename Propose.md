```java
import com.github.kusoroadeolu.annote.annotations.End;
import com.github.kusoroadeolu.annote.annotations.If;
import com.github.kusoroadeolu.annote.annotations.Return;
import com.github.kusoroadeolu.annote.annotations.Var;

class Class {

    @Var(name = "x", value = "10", type = "int")
    //I think this is a bit cleaner, the name of the variable as the annotation value
    @Var(name = "y", value = "20", type = "int")
    @Print("x + y")
    void printAdd() {

    }

    @Var(name = "x", value = "10", type = "int")
    @Var(name = "z", value = "10", type = "int")
    @If("x + z > 21")
    @Return("Here")
    @Else
    @Return("There")
    @End
    String ifStatement() {
        return null;
    }

    @Var(name = "x", value = "10", type = "int")
    @Var(name = "z", value = "10", type = "int")
    @If("x + z > 21")
    @Print("Here")
    @Else
    @Print("There")
    @End
    String ifPrintStatement() {
        return null;
    }

    @Var(name = "x", value = "10", type = "int")
    @Var(name = "z", value = "10", type = "int")
    @If("x + z > 21")
    @Return("Here")
\    String ifPrintStatementWithoutElse() {
        return null;
    }

    @Var(name = "name", value = "Victor", type = "int")
    @Loop("name != james")
    @Print("name")
    @End
    void printName() {
    }

}
```

