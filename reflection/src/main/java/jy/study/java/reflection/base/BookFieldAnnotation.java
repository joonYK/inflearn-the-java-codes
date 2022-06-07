package jy.study.java.reflection.base;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface BookFieldAnnotation {

    String value();
}
