package group144.kidyankin;

public class Main {

    public static void main(String[] args) {
        List list = new List();
        list.add(1);
        list.add(2);
        list.add(3,1);
        System.out.println(list.find(3));
        System.out.println(list.pop(2));
        System.out.println(list.pop());
        System.out.println(list.getLength());
    }
}
