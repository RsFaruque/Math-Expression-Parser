import java.util.Scanner;
import stack_parser.StackParser;


public class Main {
    public static void main(String[] args) {

        Scanner k = new Scanner(System.in);
        StackParser parser = new StackParser();

        System.out.println("Answer: " + parser.evaluate(k.next()));
        

        k.close();

    }
}