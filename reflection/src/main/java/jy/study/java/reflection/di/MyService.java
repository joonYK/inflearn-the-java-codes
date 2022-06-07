package jy.study.java.reflection.di;

public class MyService {

    @Inject
    MyRepository myRepository;

    public void execute() {
        System.out.println("MyService.execute");
        myRepository.save();
    }
}
