package jy.study.java.reflection.proxy.patternExample;

public class DefaultBookService implements BookService {

    public void rent(Book book) {
        System.out.println("rent : " + book.getTitle());
    }
}
