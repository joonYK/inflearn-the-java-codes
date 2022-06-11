package jy.study.java.reflection.proxy.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class App {

    public static void main(String[] args) {
        BookService bookService = (BookService) Proxy.newProxyInstance(
                //프록시로 만들 타입의 클래스로더
                BookService.class.getClassLoader(),
                //구현해야할 인터페이스 목록
                new Class[]{BookService.class},
                //프록시의 어떤 메서드가 호출이 될 때 어떻게 처리할지에 대한 설명
                new InvocationHandler() {
                    //리얼 서브젝트
                    BookService bookService = new DefaultBookService();

                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if (method.getName().equals("rent")) {
                            System.out.println("proxy start!!!");
                            //호출된 메서드에 리얼 서브젝트 인스턴스와 파라미터를 넘겨서 메서드 실행
                            Object invoke = method.invoke(bookService, args);
                            System.out.println("proxy end!!!");
                            return invoke;
                        }

                        return method.invoke(bookService, args);
                    }
                });

        Book book = new Book("spring");
        bookService.rent(book);
        bookService.returnBook(book);
    }
}
