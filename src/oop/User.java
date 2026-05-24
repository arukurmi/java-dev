package oop;

public class User {
    private final String id;
    private String name;
    private int age;

    public User(String id, String name, int age){
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public void printDetails() {
        System.out.println("user: " + name + " id: " + id + " age: " + age);
    }
    public String getId(){
        return id;
    }
    public void updateName(String newName) {
        this.name = newName;
    }
}
