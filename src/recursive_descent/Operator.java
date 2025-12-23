package recursive_descent;

public class Operator implements Node {
    String operator;
    Node left;
    Node right;

    public Operator(String operator, Node left, Node right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    public int evaluate() {
        switch(operator) {
            case "+" :  return left.evaluate() + right.evaluate();
            case "*" :  return left.evaluate() * right.evaluate();
            case "-" :  return left.evaluate() - right.evaluate();
            case "/" :  return left.evaluate() / right.evaluate();
            default: return 0;
        }
    }
}
