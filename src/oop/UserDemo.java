package oop;

public class UserDemo {
    public static void main(String[] args){
        User user = new User("u1", "Aryansh", 25);
        user.printDetails();
        System.out.println(user.getId());
        user.updateName("Aryansh Kurmi");
        user.printDetails();
    }
}
