package recursive_descent;

public class Operand implements Node {
    int data;

    public Operand(int data) {
        this.data = data;
    }

    public int evaluate() {
        return data;
    }
}
