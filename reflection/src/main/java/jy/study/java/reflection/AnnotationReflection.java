package jy.study.java.reflection;

import java.util.Arrays;

public class AnnotationReflection {

    public static void main(String[] args) {
        System.out.println("클래스에 선언된 애노테이션 정보 조회");
        Arrays.stream(Book.class.getAnnotations()).forEach(System.out::println);

        System.out.println();

        System.out.println("부모 타입에 선언된 애노테이션 자식도 적용");
        Arrays.stream(MyBook.class.getAnnotations()).forEach(System.out::println);

        System.out.println();

        System.out.println("해당 클래스에 직접적으로 적용된 애노테이션만 조회");
        Arrays.stream(MyBook.class.getDeclaredAnnotations()).forEach(System.out::println);

        System.out.println();

        System.out.println("필드에 선언된 애노테이션 조회");
        Arrays.stream(Book.class.getDeclaredFields()).forEach(field -> {
            Arrays.stream(field.getDeclaredAnnotations()).forEach(System.out::println);
        });

        System.out.println();

        System.out.println("애노테이션에 선언된 값 조회");
        Arrays.stream(Book.class.getDeclaredFields()).forEach(field -> {
            Arrays.stream(field.getDeclaredAnnotations()).forEach(annotation -> {
                if (annotation instanceof BookFieldAnnotation) {
                    BookFieldAnnotation bookFieldAnnotation = (BookFieldAnnotation) annotation;
                    System.out.println(bookFieldAnnotation.value());
                }
            });
        });
    }
}
