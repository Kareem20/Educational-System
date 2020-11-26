package sample;

import java.util.ArrayList;

class course {

    //name of course.
    private final String name;
    //code of course.
    private final String code;
    //the name of doctor that teach this course.
    private final String doctorName;
    // list of student that registered in this course.
    private final ArrayList<student> students;
    //list of assignment that this course has.
    private final ArrayList<Assignment> assignments;

    course(String name, String code, String doctorName) {
        this.name = name;
        this.code = code;
        this.doctorName = doctorName;
        this.students = new ArrayList<>();
        this.assignments = new ArrayList<>();
    }

    /*method to get the name of course.*/
    String getName() {
        return name;
    }

    /*method to get the code of course.*/
    String getCode() {
        return code;
    }

    /*method to get the name of doctor that teach this course.*/
    String getDoctorName() {
        return doctorName;
    }

    /*method to get the list of student that registered in this course.*/
    ArrayList<student> getStudents() {
        return students;
    }

    /*method to get the list of assignment that this course has.*/
    ArrayList<Assignment> getAssignments() {
        return assignments;
    }
}
