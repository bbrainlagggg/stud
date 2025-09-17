import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>(Arrays.asList(10, 20, 30));
        nums.add(40);
        System.out.println("Список: " + nums);          
        System.out.println("Первый элемент: " + nums.get(0));
        System.out.println("Длина: " + nums.size());

        
        Stack<String> stack = new Stack<>();
        stack.push("A");                                  
        stack.push("B");
        System.out.println("Верх стека: " + stack.peek()); 
        String top = stack.pop();                         
        System.out.println("Сняли: " + top);
        System.out.println("Стек пуст? " + stack.empty());
    }
}
