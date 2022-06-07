package jy.study.java.reflection.base.exec;

import jy.study.java.reflection.base.Book;
import jy.study.java.reflection.base.MyBook;

import java.lang.reflect.Modifier;
import java.util.Arrays;

public class ClassInfoInquiry {

    public static void main(String[] args) {
        //Book 타입의 Class 인스턴스를 가져오는 방법.
        //1. 타입.class로 접근
        Class<Book> bookClass = Book.class;

        //2. 인스턴스의 getClass()로 접근.
        Book book = new Book();
        Class<? extends Book> bookClass1 = book.getClass();

        //3. FQCN을 알고있는 경우 Class.forName()로 접근.
        try {
            Class<?> bookClass2 = Class.forName("jy.study.java.reflection.base.Book");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("1. public 필드들");
        Arrays.stream(bookClass.getFields()).forEach(System.out::println);

        System.out.println();

        System.out.println("2. 모든 필드들");
        Arrays.stream(bookClass.getDeclaredFields()).forEach(System.out::println);

        System.out.println();

        System.out.println("3. 모든 필드 중 특정 필드 (name 필드 출력)");
        try {
            System.out.println(bookClass.getDeclaredField("name"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        System.out.println();

        System.out.println("4. 모든 필드의 값을 출력 (값 출력시 인스턴스 필요)");
        Arrays.stream(bookClass.getDeclaredFields()).forEach(field -> {
            try {
                //접근이 불가능 필드(private 등) 접근 가능하도록 설정
                field.setAccessible(true);
                System.out.printf("field : %s,   value : %s\n", field, field.get(book));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });

        System.out.println();

        System.out.println("5. 모든 메서드 출력 (상속받은 메서드까지 모두)");
        Arrays.stream(bookClass.getMethods()).forEach(System.out::println);

        System.out.println();

        System.out.println("6. 생성자들");
        Arrays.stream(bookClass.getConstructors()).forEach(System.out::println);

        System.out.println();

        System.out.println("7. 수퍼클래스");
        System.out.println(MyBook.class.getSuperclass());

        System.out.println();

        System.out.println("8. 인터페이스");
        Arrays.stream(MyBook.class.getInterfaces()).forEach(System.out::println);

        System.out.println();

        System.out.println("9. 필드의 접근제어자, static 여부, final 여부 등 확인.");
        Arrays.stream(bookClass.getDeclaredFields()).forEach(field -> {
            int modifiers = field.getModifiers();
            System.out.println(field);
            System.out.printf("isPrivate : %s\n", Modifier.isPrivate(modifiers));
            System.out.printf("isStatic : %s\n", Modifier.isStatic(modifiers));
        });

        System.out.println();

        System.out.println("10. 메서드의 접근제어자, static 여부, final 여부 등 확인.");
        Arrays.stream(bookClass.getMethods()).forEach(method -> {
            int modifiers = method.getModifiers();
            System.out.println(method);
            System.out.printf("isPrivate : %s\n", Modifier.isPrivate(modifiers));
            System.out.printf("isStatic : %s\n", Modifier.isStatic(modifiers));
        });
    }
}
