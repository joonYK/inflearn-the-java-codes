package jy.study.java;

//모임
public class Moim {

    public Moim(int maxNumberOfAttendees, int numberOfEnrollment) {
        this.maxNumberOfAttendees = maxNumberOfAttendees;
        this.numberOfEnrollment = numberOfEnrollment;
    }

    //참가자 max
    private int maxNumberOfAttendees;

    //현재 신청수
    private int numberOfEnrollment;

    public boolean isEnrollmentFull() {
        if (maxNumberOfAttendees == 0)
            return false;

        if (numberOfEnrollment < maxNumberOfAttendees)
            return false;

        return true;
    }
}
