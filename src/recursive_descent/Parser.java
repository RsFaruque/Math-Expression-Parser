package recursive_descent;

import java.util.ArrayList;

public class Parser {
    public Node buildTree(String exp) {
        ArrayList<String> tokens = tokenize(exp);
        return addNode(tokens);
    }

    public Node addNode(ArrayList<String> tokens) { 
        debug("addNode called");
        if (tokens.size() == 1) {
            return new Operand(Integer.parseInt(tokens.get(0)));
        }
        
        String op = "";
        int i = 0;
        for (; i < tokens.size(); i++) {
            if (!stringIsDigit(tokens.get(i))
                && !tokens.get(i).equals("(")
                && !tokens.get(i).equals(")")
            ) {
                op = tokens.get(i);
                break;
            }
        }
        return new Operator(
            op, 
            addNode(slice(tokens, 0, i)), 
            addNode(slice(tokens, i+1, tokens.size()))
        );
    }


    public ArrayList<String> slice(ArrayList<String> list, int start, int end) {
        ArrayList<String> s = new ArrayList<String>();
        for (int i = start; i < end; i++) {
            s.add(list.get(i));
        }
        // debug(s.toString());
        return s;
    }

    public boolean stringIsDigit(String x) {
        for (int i = 0; i < x.length(); i++) {
            if (!Character.isDigit(x.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<String> tokenize(String exp) {
        ArrayList<String> tokens = new ArrayList<String>();
        
        String x = "";
        for (int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);
            if (Character.isDigit(ch)) {
                x += ch;
            } else {
                tokens.add(x);
                x = "";
                tokens.add(""+ch);
            }
        }
        if (x.length() != 0) {
            tokens.add(x);
        }
        return tokens;
    }
    void debug(String x) {
        System.out.println("Debug: slice: " + x);
    }
}
