package jy.study.java.reflection.proxy.classProxy.bytebuddy;

import jy.study.java.reflection.proxy.classProxy.Book;
import jy.study.java.reflection.proxy.classProxy.BookService;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.InvocationHandlerAdapter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static net.bytebuddy.matcher.ElementMatchers.named;

public class App {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //프록시 클래스를 얻음.
        Class<? extends BookService> proxyClass = new ByteBuddy()
                //프록시로 만들 타입의 서브 클래스를 만들기위해 타입 지정.
                .subclass(BookService.class)
                //특정 메서드별로 작업을 추가시 method 메서드로 추가.
                .method(named("rent")).intercept(InvocationHandlerAdapter.of(new InvocationHandler() {
                    BookService bookService = new BookService();

                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("rent start");
                        Object invoke = method.invoke(bookService, args);
                        System.out.println("rent end");
                        return invoke;
                    }
                }))
                //생성
                .make()
                //클래스 로딩은 해당 타입을 로드한 클래스로더를 지정.
                .load(BookService.class.getClassLoader())
                //로딩된것을 가져옴.
                .getLoaded();

        BookService bookService = proxyClass.getConstructor(null).newInstance();

        Book book = new Book("java");
        bookService.rent(book);
        bookService.returnBook(book);
    }
}
