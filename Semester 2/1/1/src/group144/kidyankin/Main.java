package group144.kidyankin;

public class Main {

    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.add(1);
        stack.add(4);
        stack.add(9);
        System.out.println(stack.getSize());
        stack.pop();
        System.out.println(stack.pop());
        System.out.println(stack.isEmpty());
        stack.pop();
        System.out.println(stack.isEmpty());
    }
}
