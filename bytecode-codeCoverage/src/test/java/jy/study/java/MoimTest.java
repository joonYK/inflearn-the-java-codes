package jy.study.java;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class MoimTest {

    @Test
    public void isFull() {
        Moim moim = new Moim(100, 10);
        assertFalse(moim.isEnrollmentFull());
    }
}
