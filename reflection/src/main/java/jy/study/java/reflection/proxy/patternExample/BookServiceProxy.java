package jy.study.java.reflection.proxy.patternExample;

public class BookServiceProxy implements BookService {

    private BookService bookService;

    public BookServiceProxy(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void rent(Book book) {
        System.out.println("rent start!!!");
        bookService.rent(book);
    }
}
