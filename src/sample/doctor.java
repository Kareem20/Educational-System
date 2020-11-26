package sample;

import java.util.ArrayList;

/*
 * class student extend User class
 * */
class doctor extends user {

    //list of course that doctor teaching it.
    private final ArrayList<course> teachingCourse;

    doctor(String username, int ID, String fullName, String email, String password) {
        //extent from user class.
        super(username, ID, fullName, email, password);
        //declare the array list.
        teachingCourse = new ArrayList<>();
    }

    /*
     * return method to get the list of course that doctor teaching it.
     *
     * return array list of courses. */
    ArrayList<course> getTeachingCourse() {
        return teachingCourse;
    }
}
