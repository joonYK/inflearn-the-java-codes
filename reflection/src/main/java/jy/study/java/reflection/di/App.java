package jy.study.java.reflection.di;

public class App {

    public static void main(String[] args) {
        MyService myService = ContainerService.getObject(MyService.class);
        myService.execute();
    }
}
