import com.github.kusoroadeolu.annote.*;

void main() {

    //AnnotationParser.run(new Test(), "smth");
    Map<String, Integer> map = new HashMap<>(Map.of("x", 12, "y", 2));
    String expr = "x + y * 2".replaceAll("\\s++", "");

    StringBuilder builder = new StringBuilder();
    IO.println(expr);
    for (int i = 0; i < expr.length(); i++){
        char c = expr.charAt(i);
        Object val = map.get(String.valueOf(c));
        if (val != null){
            builder.append(val);
        }else {
            builder.append(c);
        }
    }

    String expressed = builder.toString();
    IO.println(expressed);
    builder.setLength(0);

}


void assign(){

}

