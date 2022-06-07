package jy.study.java.reflection.di;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContainerServiceTest {

    @Test
    public void getObject_BookRepository() {
        MyRepository myRepository = ContainerService.getObject(MyRepository.class);
        assertNotNull(myRepository);
    }

    @Test
    public void getObject_BookService() {
        MyService myService = ContainerService.getObject(MyService.class);
        assertNotNull(myService);
        assertNotNull(myService.myRepository);
    }

}