package jy.study.java.reflection.base;

import java.lang.annotation.*;

//클래스를 메모리로 로드시에 이 애노테이션 정보를 유지해야 리플렉션 api를 통해 조회 가능하기 때문에 RUNTIME 옵션 설정.
@Retention(RetentionPolicy.RUNTIME)
//어디에 선언 가능한지 설정. 아래 설정은 타입과 필드에만 지정 가능.
@Target({ElementType.TYPE, ElementType.FIELD})
//상속이 되는 애노테이션으로 설정. 부모타입에 선언된 애노테이션을 자식도 적용됨.
@Inherited
public @interface BookAnnotation {

    //value 라는 이름의 값은 애노테이션을 사용하는곳에서 값 지정시 value 라고 명칭 지정하지 않아도 됨.
    //@MyAnnotation(value = "value!"), @MyAnnotation("value!") 둘 다 가능.
    String value() default "value";

    String name() default "jy";

    int number() default 100;
}
