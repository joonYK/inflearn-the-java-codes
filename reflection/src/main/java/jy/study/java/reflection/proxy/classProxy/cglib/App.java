package jy.study.java.reflection.proxy.classProxy.cglib;

import jy.study.java.reflection.proxy.classProxy.Book;
import jy.study.java.reflection.proxy.classProxy.BookService;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class App {
    public static void main(String[] args) {
        MethodInterceptor handler = new MethodInterceptor() {
            BookService bookService = new BookService();

            @Override
            public Object intercept(Object obj, Method method,Object[] args, MethodProxy proxy) throws Throwable {
                if (method.getName().equals("rent")) {
                    System.out.println("proxy start");
                    Object invoke = method.invoke(bookService, args);
                    System.out.println("proxy end");
                    return invoke;
                }
                return method.invoke(bookService, args);
            }
        };

        BookService bookService = (BookService) Enhancer.create(
            //프록시로 만들어질 타입
            BookService.class,
            //다이나믹 프록시의 InvocationHandler 같은 handler.
            //프록시 객체의 메서드를 호출할떄마다 어떤일을 해야할지 정의.
            handler
        );

        Book book = new Book("java");
        bookService.rent(book);
        bookService.returnBook(book);
    }
}
