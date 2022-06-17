package jy.study.java;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


// 인터페이스, 클래스, Enum 지정 가능.
@Target(ElementType.TYPE)
//이 애노테이션은 컴파일 타임에 쓰고 바이트코드에는 필요없기 때문에 소스코드까지만 유지.
@Retention(RetentionPolicy.SOURCE)
public @interface Magic {
}
