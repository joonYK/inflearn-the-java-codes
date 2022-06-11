package jy.study.java.reflection.proxy.dynamicProxy;

public class DefaultBookService implements BookService {

    public void rent(Book book) {
        System.out.println("rent : " + book.getTitle());
    }

    @Override
    public void returnBook(Book book) {
        System.out.println("returnBook : " + book.getTitle());
    }
}
