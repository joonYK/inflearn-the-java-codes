package jy.study.java.reflection.base.exec;

import jy.study.java.reflection.base.Item;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassUpdateAndExecute {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Class<Item> itemClass = Item.class;

        System.out.println("파라미터 없는 생성자로 인스턴스 생성");
        Constructor<Item> constructor = itemClass.getConstructor(null);
        Item item = constructor.newInstance();
        System.out.println(item);

        System.out.println();

        System.out.println("파라미터 있는 생성자로 인스턴스 생성");
        Constructor<Item> constructor1 = itemClass.getConstructor(String.class);
        Item item1 = constructor1.newInstance("아이템1");
        System.out.println(item1.getName());

        System.out.println();

        System.out.println("static 필드 조회");
        Field staticField = itemClass.getDeclaredField("type");
        //static 필드기 때문에 인스턴스 파라미터가 필요 없음.
        Object o = staticField.get(null);
        System.out.println(o);

        System.out.println();

        System.out.println("static 필드 값 변경");
        Field staticField1 = itemClass.getDeclaredField("type");
        //static 필드기 때문에 인스턴스는 null
        staticField1.set(null, "updatedItem");
        System.out.println(staticField.get(null));

        System.out.println();

        System.out.println("인스턴스의 필드 값 조회");
        Field field = itemClass.getDeclaredField("name");
        field.setAccessible(true);
        System.out.println(field.get(item));

        System.out.println();

        System.out.println("인스턴스의 필드 값 변경");
        Field field1 = itemClass.getDeclaredField("name");
        field1.setAccessible(true);
        field1.set(item, "updatedName");
        System.out.println(field.get(item));

        System.out.println();

        System.out.println("인스턴스의 파라미터 없는 메서드 호출");
        Method method1 = itemClass.getDeclaredMethod("method1");
        method1.invoke(item);

        System.out.println();

        System.out.println("인스턴스의 파라미터 있는 메서드 호출");
        Method sum = itemClass.getDeclaredMethod("sum", int.class, int.class);
        System.out.println(sum.invoke(item, 1, 2));
    }
}
