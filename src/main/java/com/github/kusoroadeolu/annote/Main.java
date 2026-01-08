import com.github.kusoroadeolu.annote.MethodAnnotationReader;
import com.github.kusoroadeolu.annote.Utils;
import com.github.kusoroadeolu.annote.conditions.ConditionParser;
import com.github.kusoroadeolu.annote.strings.StringParser;
import com.github.kusoroadeolu.annote.tokenizer.Condition;
import com.github.kusoroadeolu.annote.tokenizer.Operator;

import static com.github.kusoroadeolu.annote.MethodAnnotationReader.*;

void main() {
String expr = new StringParser().parse("var1+1");
IO.println(expr);

}



