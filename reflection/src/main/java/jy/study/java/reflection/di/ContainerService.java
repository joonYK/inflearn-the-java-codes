package jy.study.java.reflection.di;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class ContainerService {

    public static <T> T getObject(Class<T> classType) {
        T instance = createInstance(classType);
        Arrays.stream(classType.getDeclaredFields()).forEach(field -> {
            //@Inject 애노테이션이 선언된 필드 확인.
            if (field.getAnnotation(Inject.class) != null) {
                //해당 필드의 인스턴스 생성
                Object typeInstance = createInstance(field.getType());
                field.setAccessible(true);
                try {
                    field.set(instance, typeInstance);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        return instance;
    }

    private static <T> T createInstance(Class<T> classType) {
        try {
            return classType.getConstructor(null).newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw  new RuntimeException(e);
        }
    }
}
