package com.github.kusoroadeolu.annote.math;

import java.util.*;


import static com.github.kusoroadeolu.annote.math.Operator.fromChar;
import static java.lang.Double.parseDouble;

class MathTokenizer {
    private final static char DOT = '.';
    private final static Set<Character> SET = Set.of('+', '-', '/', '%', '^', '*', ')', '(');


    // Note: brackets handled separately
    public List<Token> reform(String s){
        Deque<Operator> operators = new ArrayDeque<>();
        StringBuilder numBuilder = new StringBuilder();
        List<Token> output = new ArrayList<>();
        for (int i = 0; i < s.length(); ++i){
            char c = s.charAt(i);
            if (isNum(c)){
                numBuilder.append(c);
            } else if (isOperator(c)) {

                if (!numBuilder.isEmpty()){
                    double d = parseDouble(numBuilder.toString());
                    output.add(new Token(d));
                    numBuilder.setLength(0);
                }

                final Operator curr = fromChar(c);
                if (curr.isLeftBracket()){
                    operators.push(curr);
                    continue;
                }

                Operator op = operators.peek();
                if (curr.isRightBracket()){
                    operators.push(curr);
                    while (op != null && !op.isLeftBracket()){
                        Operator add = operators.pop();
                        if (add.isBracket()) continue;
                        output.add(new Token(add));
                        op = operators.peek();
                    }
                    continue;
                }


                while (op != null && curr.hasLessOrEqualPrecedence(op)){
                    output.add(new Token(operators.pop()));
                    op = operators.peek();
                }

                operators.push(curr);
            }
        }
        //END OF LOOP


        if (!numBuilder.isEmpty()){
            double d = parseDouble(numBuilder.toString());
            output.add(new Token(d)); //Add the last number
        }

        while (!operators.isEmpty()){
            Operator o = operators.pop(); //Pop the remaining ops onto the list
            if (o.isBracket()) continue;
            output.add(new Token(o));
        }

        return output;

    }

    public static boolean isOperator(char c){
        return SET.contains(c);
    }

    public static boolean isNum(char c){
        return Character.isDigit(c) || c == DOT;
    }
}
