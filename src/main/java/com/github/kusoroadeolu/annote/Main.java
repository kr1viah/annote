import com.github.kusoroadeolu.annote.AnnoteRunner;
import com.github.kusoroadeolu.annote.Runner;
import com.github.kusoroadeolu.annote.TestClass;

void main() {
    Runner r = AnnoteRunner.newRunner(TestClass.class);
    r.run("fizzbuzz");
}



