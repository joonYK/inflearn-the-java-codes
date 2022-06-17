package jy.study.java;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.*;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.util.Set;

public class MagicMojaProcessor extends AbstractProcessor {

    //이 프로세서가 어떤 애노테이션을 처리할 것인지 오버라이딩
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        //이 프로세서가 처리할 애노테이션의 문자열을 전달
        return Set.of(Magic.class.getName());
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        //Magic 애노테이션이 붙은 엘리먼트(패키지, 인터페이스, 클래스, 메서드, 필드 등)들 조회
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(Magic.class);

        for (Element element : elements) {
            Name simpleName = element.getSimpleName();

            //엘리먼트의 타입을 조회해서 인터페이스가 아니면 컴파일이 되지 않도록 에러 처리
            if (element.getKind() != ElementKind.INTERFACE) {
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
                        "Magic annotation can not be used on " + simpleName);
            } else {
                processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Processing " + simpleName);
            }

            TypeElement typeElement = (TypeElement) element;
            //클래스 정보 조회
            ClassName className = ClassName.get(typeElement);

            //메서드 생성
            MethodSpec pullOut = MethodSpec.methodBuilder("pullOut")
                    //접근제어자
                    .addModifiers(Modifier.PUBLIC)
                    //return 타입
                    .returns(String.class)
                    .addStatement("return $S", "Rabbit!")
                    .build();

            //클래스 생성
            TypeSpec magicMoja = TypeSpec.classBuilder("MagicMoja")
                    //public class
                    .addModifiers(Modifier.PUBLIC)
                    //Magic 애노테이션이 붙어있는 인터페이스 구현
                    .addSuperinterface(className)
                    //위에서 생성한 메서드 pullOut 적용
                    .addMethod(pullOut)
                    .build();


            //소스코드, 클래스코드, 리소스를 생성할 수 있는 인터페이스
            Filer filer = processingEnv.getFiler();
            try {
                JavaFile.builder(className.packageName(), magicMoja)
                        .build()
                        //filer에 소스코드 생성
                        .writeTo(filer);
            } catch (IOException e) {
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "FATAL ERROR: " + e);
            }
        }

        //true 반환시 다른 애노테이션 프로세서에서 처리하지 않음
        return true;
    }
}
