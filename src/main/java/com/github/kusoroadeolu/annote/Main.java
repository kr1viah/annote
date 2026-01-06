import com.github.kusoroadeolu.annote.*;

void main() {

    ConditionExpr check = new ConditionExpr.Equals(
            new ArithmeticExpr.Add(new ArithmeticExpr.ArithmeticValue(10), new ArithmeticExpr.ArithmeticValue(5)),
            new ArithmeticExpr.ArithmeticValue(15)
    );

    IO.println(check.evaluate().value());

}


void assign(){

}

