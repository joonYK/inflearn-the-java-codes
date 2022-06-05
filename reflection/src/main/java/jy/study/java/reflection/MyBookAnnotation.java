package jy.study.java.reflection;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface MyBookAnnotation {

    String value() default "MyBook";
}
