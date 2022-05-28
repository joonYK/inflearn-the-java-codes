package jy.study.java.bytebuddy.notBeforehandLoad;

import jy.study.java.bytebuddy.Moja;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.pool.TypePool;

import java.io.File;
import java.io.IOException;

import static net.bytebuddy.matcher.ElementMatchers.named;

public class Masulsa {

    public static void main(String[] args) {
        ClassLoader classLoader = Masulsa.class.getClassLoader();
        TypePool typePool = TypePool.Default.of(classLoader);

        try {
            new ByteBuddy().redefine(
                    //Moja 클래스를 미리 읽어들이지 않도록 함.
                    typePool.describe("jy.study.java.bytebuddy.moja").resolve(), ClassFileLocator.ForClassLoader.of(classLoader))
                    .method(named("pullOut")).intercept(FixedValue.value("Rabbit!"))
                    .make().saveIn(new File("/Users/jeff/study/inflearn-the-java-codes/bytecode-changing/out/production/classes/"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //위에서 미리 Moja를 읽어들이지 않고 재정의했기 때문에 바로 실행 가능.
        //하지만 클래스로드 순서에 의존적이면서 다른곳에서 이미 Moja클래스를 사용하면서 읽어들였다면 무쓸모...
        System.out.println(new Moja().pullOut());
    }
}
