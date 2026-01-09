import com.github.kusoroadeolu.annote.AnnotationParser;
import com.github.kusoroadeolu.annote.Test;
import com.github.kusoroadeolu.annote.statements.Result;

void main() {
    AnnotationParser annotationParser = new AnnotationParser(Test.class);
    Result r = annotationParser.read("call");



}



