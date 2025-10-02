import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        String x = "x", y = "y", z = "z";
        stack.push(x);
        stack.push(y);
        stack.push(z);
        String top = stack.pop();
        System.out.println(top);
    }
}
