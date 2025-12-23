package stack_parser;

import java.util.ArrayList;
import java.util.Stack;

public class StackParser {
    public int evaluate(String exp) {
        ArrayList<String> tokens = tokenize(exp);
        ArrayList<String> postfix = getPostfixExpression(tokens);
        Stack<String> stack = new Stack<String>();
        int out = 0;

        for (int i = 0; i < postfix.size(); i++) {
            switch (postfix.get(i)) {
                case "+": {
                    out = Integer.parseInt(stack.pop()) + Integer.parseInt(stack.pop());
                    stack.push("" + out);
                    break;
                }
                case "-": {
                    int second = Integer.parseInt(stack.pop());
                    out = Integer.parseInt(stack.pop()) - second;
                    stack.push("" + out);
                    break;
                }
                case "*": {
                    out = Integer.parseInt(stack.pop()) * Integer.parseInt(stack.pop());
                    stack.push("" + out);
                    break;
                }
                case "/": {
                    int denominator = Integer.parseInt(stack.pop()); 
                    out = Integer.parseInt(stack.pop()) / denominator;
                    stack.push("" + out);
                    break;
                }
                default: {
                    stack.push(postfix.get(i));
                }
            }
        }
        return Integer.parseInt(stack.pop());

    }

    public ArrayList<String> getPostfixExpression(ArrayList<String> tokens) {
        Stack<String> operators = new Stack<String>();
        ArrayList<String> postfix = new ArrayList<String>();

        for (int i = 0; i < tokens.size(); i++) {

            if (stringIsOperator(tokens.get(i))) {
                int expOp = operatorPriotiy(tokens.get(i));

                while (true) {
                    try {
                        int stack = operatorPriotiy(operators.lastElement());
                        if (stack >= expOp) {
                            postfix.add(operators.pop());
                        } else {
                            operators.push(tokens.get(i));
                            break;
                        }
                    } catch (java.util.NoSuchElementException e) {
                        operators.push(tokens.get(i));
                        break;
                    }
                }
            } else {
                postfix.add(tokens.get(i));
            }
        }
        if (operators.size() != 0) {
            while (!operators.isEmpty()) {
                postfix.add(operators.pop());
            }
        }
        return postfix;
    }

    public boolean stringIsOperator(String x) {
        if (x.charAt(0) == '+'
            || x.charAt(0) == '-'
            || x.charAt(0) == '/'
            || x.charAt(0) == '*') {
                return true;
        }
        return false;
    }
    public boolean stringIsDigit(String x) {
        for (int i = 0; i < x.length(); i++) {
            if (!Character.isDigit(x.charAt(i))) {
                return false;
            }
        }
        return true;
    }


    public int operatorPriotiy(String x) {
        if (x.equals("*") || x.equals("/")) {
            return 1;
        } else {
            return 0;
        }
    }

    public ArrayList<String> tokenize(String exp) {
        ArrayList<String> tokens = new ArrayList<String>();

        String x = "";
        char ch;
        for (int i = 0; i < exp.length(); i++) {
            ch = exp.charAt(i);
            if (Character.isDigit(ch)) {
                x += ch;
            } else {
                tokens.add(x);
                x = "";
                tokens.add("" + ch);
            }
        }
        if (x.length() > 0) {
            tokens.add(x);
        }
        return tokens;
    }

    void debug(String x) {
        System.out.println("Debug: " + x);
    }
}