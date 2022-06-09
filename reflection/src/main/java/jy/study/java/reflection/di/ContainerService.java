package jy.study.java.reflection.di;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class ContainerService {

    public static <T> T getObject(Class<T> classType) {
        //파라미터로 받은 클래스 타입의 인스턴스 생성
        T instance = createInstance(classType);
        //파라미터로 받은 클래스타입에 선언된 필드들 순회
        Arrays.stream(classType.getDeclaredFields()).forEach(field -> {
            //@Inject 애노테이션이 선언된 필드인지 확인
            if (field.getAnnotation(Inject.class) != null) {
                //해당 필드의 인스턴스 생성
                Object typeInstance = createInstance(field.getType());
                field.setAccessible(true);
                try {
                    //필드의 인스턴스 주입
                    field.set(instance, typeInstance);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        return instance;
    }

    //파라미터로 받은 클래스 타입의 인스턴스 생성
    private static <T> T createInstance(Class<T> classType) {
        try {
            //기본 생성자로 인스턴스 생성
            return classType.getConstructor(null).newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw  new RuntimeException(e);
        }
    }
}
