package jy.study.java.reflection.base;

public class Item {

    public static String type = "item";

    private String name = "name";

    public Item() {
    }

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void method1() {
        System.out.println("method1");
    }

    public int sum(int left, int right) {
        return left + right;
    }
}
