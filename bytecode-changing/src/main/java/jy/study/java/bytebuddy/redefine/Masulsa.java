package jy.study.java.bytebuddy.redefine;

import jy.study.java.bytebuddy.Moja;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;

import java.io.File;
import java.io.IOException;

import static net.bytebuddy.matcher.ElementMatchers.named;

public class Masulsa {

    public static void main(String[] args) {
        //Moja 클래스의 바이트 코드를 변경.
        try {
            new ByteBuddy().redefine(Moja.class)
                    .method(named("pullOut")).intercept(FixedValue.value("Rabbit!"))
                    .make().saveIn(new File("/Users/jeff/study/inflearn-the-java-codes/bytecode-changing/out/production/classes/"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //바이트코드를 먼저 변경하고나서 실행
        //System.out.println(new Moja().pullOut());

    }
}
