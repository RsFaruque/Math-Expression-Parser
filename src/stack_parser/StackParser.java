package stack_parser;

import java.util.ArrayList;
import java.util.Stack;
import java.util.HashMap;

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
                case "^": {
                    double power = Double.parseDouble(stack.pop());
                    out = (int) Math.pow(Double.parseDouble(stack.pop()), power);
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
        HashMap<String, Integer> operators = new HashMap<String, Integer>();  // the values set priority
        operators.put("+", 0);
        operators.put("-", 0);
        operators.put("*", 1);
        operators.put("/", 1);
        operators.put("^", 2);

        Stack<String> operatorStack = new Stack<String>();
        ArrayList<String> postfix = new ArrayList<String>();

        for (int i = 0; i < tokens.size(); i++) {

            if (operators.containsKey(tokens.get(i))) {
                int expOp = operators.get(tokens.get(i));

                while (true) {
                    try {
                        int stack = operators.get(operatorStack.lastElement());
                        if (stack >= expOp) {
                            postfix.add(operatorStack.pop());
                        } else {
                            operatorStack.push(tokens.get(i));
                            break;
                        }
                    } catch (java.util.NoSuchElementException e) {
                        operatorStack.push(tokens.get(i));
                        break;
                    }
                }
            } else {
                postfix.add(tokens.get(i));
            }
        }
        if (operatorStack.size() != 0) {
            while (!operatorStack.isEmpty()) {
                postfix.add(operatorStack.pop());
            }
        }
        return postfix;
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
}