// import recursive_descent.Parser;
// import recursive_descent.Node;
import java.util.ArrayList;
import java.util.Scanner;
import stack_parser.StackParser;


public class Main {
    public static void main(String[] args) {

        Scanner k = new Scanner(System.in);
        // String exp2 = k.next();
        // Parser parser = new Parser();
        StackParser parser = new StackParser();

        ArrayList<String> postfix = parser.postfixExpression(parser.tokenize(k.next()));
        System.out.println(postfix.toString());

        // System.out.println(parser.stringIsDigit("10"));
        // Node root = parser.buildTree(exp2);
        // int out = root.evaluate();

        // System.out.println(out);


        k.close();

    }
}