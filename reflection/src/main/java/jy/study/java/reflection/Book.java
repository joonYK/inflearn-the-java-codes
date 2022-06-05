package jy.study.java.reflection;

@BookAnnotation("Book")
public class Book {

    @BookFieldAnnotation("type1")
    private static String type1 = "ITEM";

    @BookFieldAnnotation("type2")
    private static final String type2 = "BOOK";

    @BookFieldAnnotation("name")
    private String name;

    @BookFieldAnnotation("price")
    public int price = 10000;

    @BookFieldAnnotation("author")
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
