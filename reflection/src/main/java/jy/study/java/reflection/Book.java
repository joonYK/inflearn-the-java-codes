package jy.study.java.reflection;

public class Book {

    private static String type1 = "ITEM";

    private static final String type2 = "BOOK";

    private String name;

    public int price = 10000;

    protected String author = "작가";

    public Book() {
    }

    public Book(String name, int price, String author) {
        this.name = name;
        this.price = price;
        this.author = author;
    }

    private void method1() {
        System.out.println("method1");
    }

    public void method2() {
        System.out.println("method2");
    }

    public Integer method3() {
        return 10000;
    }
}
