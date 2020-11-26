package sample;

import java.util.ArrayList;

/*
 * class student extend User class
 * */
class student extends user {

    //list of courses that student has registered in.
    private final ArrayList<course> registerCourses;
    //list of assignments that student has submitted in.
    private final ArrayList<Assignment> assignments;


    student(String username, int ID, String fullName, String email, String password) {
        //extent from user class.
        super(username, ID, fullName, email, password);
        //declare the array list of registered courses.
        registerCourses = new ArrayList<>();
        //declare the array list.
        assignments = new ArrayList<>();
    }


    /*
     *method get the courses that student registered in it.
     *
     * return Array list of courses.
     * */
    ArrayList<course> getRegisterCourses() {
        return registerCourses;
    }
    /*
     *method get the assignment that student submitted in it.
     *
     * return Array list of assignments.
     * */
    ArrayList<Assignment> getAssignments() {
        return assignments;
    }
}
