package jy.study.java.reflection.proxy.classProxy;

public class BookService {

    public void rent(Book book) {
        System.out.println("rent : " + book.getTitle());
    }

    public void returnBook(Book book) {
        System.out.println("returnBook : " + book.getTitle());
    }
}
