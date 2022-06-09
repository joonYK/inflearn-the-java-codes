package jy.study.java.reflection.proxy.patternExample;

public class App {

    public static void main(String[] args) {
        BookService bookService = new BookServiceProxy(new DefaultBookService());
        Book book = new Book("spring");
        bookService.rent(book);
    }
}
