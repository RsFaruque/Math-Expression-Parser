package stack_parser;

import java.util.ArrayList;
import java.util.Stack;
import java.util.HashMap;

public class StackParser {
    
    public double evaluate(String exp) {        
        ArrayList<String> tokens = tokenize(exp);
        ArrayList<String> postfix = getPostfixExpression(tokens);
        Stack<String> stack = new Stack<String>();
        double out = 0;

        for (int i = 0; i < postfix.size(); i++) {
            switch (postfix.get(i)) {
                case "+": {
                    out = Double.parseDouble(stack.pop()) + Double.parseDouble(stack.pop());
                    stack.push("" + out);
                    break;
                }
                case "-": {
                    double second = Double.parseDouble(stack.pop());
                    out = Double.parseDouble(stack.pop()) - second;
                    stack.push("" + out);
                    break;
                }
                case "*": {
                    out = Double.parseDouble(stack.pop()) * Double.parseDouble(stack.pop());
                    stack.push("" + out);
                    break;
                }
                case "/": {
                    double denominator = Double.parseDouble(stack.pop()); 
                    out = Double.parseDouble(stack.pop()) / denominator;
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
        return Double.parseDouble(stack.pop());
    }

    public ArrayList<String> getPostfixExpression(ArrayList<String> tokens) {
        HashMap<String, Integer> operators = new HashMap<String, Integer>();  // the values set priority
        operators.put("+", 0);
        operators.put("-", 0);
        operators.put("*", 1);
        operators.put("/", 1);
        operators.put("^", 2);
        operators.put("(", 3);
        operators.put(")", -3);
        int addPriority = 0; // use this for parenthesis

        Stack<String> operatorStack = new Stack<String>();
        ArrayList<String> postfix = new ArrayList<String>();

        for (int i = 0; i < tokens.size(); i++) {

            if (operators.containsKey(tokens.get(i))) {
                String operator = tokens.get(i);  // operator from expression
                
                // account for parenthesis
                if (operator.equals("(") || operator.equals(")")) {
                    addPriority += operators.get(operator);
                    continue;
                }
                
                int expOp = operators.get(operator) + addPriority;

                while (true) {
                    try {
                        int stack = operators.get(operatorStack.lastElement());  // top operator in the stack
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
        String prevState = "digit";
        for (int i = 0; i < exp.length(); i++) {  // 10*(8+2)
            ch = exp.charAt(i);
            if (Character.isDigit(ch) || ch == '.') {
                x += ch;
                prevState = "digit";
            } else if (ch == ' ') {
                continue;
            } else {
                if (prevState.equals("operator")) {
                    tokens.add("" + ch);
                } else {
                    tokens.add(x);
                    x = "";
                    tokens.add("" + ch);
                }
                prevState = "operator";
            }
        }
        if (x.length() > 0) {
            tokens.add(x);
        }
        return tokens;
    }

    public static void debug(String x) {
        System.out.println("Debug: " + x);
    }
}