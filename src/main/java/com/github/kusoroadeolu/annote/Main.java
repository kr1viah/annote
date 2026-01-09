import com.github.kusoroadeolu.annote.AnnotationParser;
import com.github.kusoroadeolu.annote.Test;

void main() {
    AnnotationParser annotationParser = new AnnotationParser(Test.class);
    annotationParser.read("smth");


}



