package sample;

import java.util.ArrayList;
import java.util.Objects;

/*
 * class to store dummy data
 * and make some functions.
 * */
class Data {

    // list of student as database.
    private static final ArrayList<student> students = new ArrayList<>();
    // list of doctor as database.
    private static final ArrayList<doctor> doctors = new ArrayList<>();
    // list of course as database.
    private static final ArrayList<course> courses = new ArrayList<>();

    Data() {
        /*Dummy data for my database (courses , students and doctors).*/
        courses.add(new course("CS", "cs124", "Ramy"));
        courses.add(new course("Programming I", "cs221", "Ali"));
        courses.add(new course("OOP", "cs324", "Hussin"));
        courses.add(new course("English", "en412", "Hussn"));
        courses.add(new course("physics I", "ph212", "Mohmmed"));
        courses.add(new course("Networking", "it431", "Omer"));
        courses.add(new course("Programming II", "cs223", "karam"));


        doctors.add(new doctor("Ramy", 123, "Ramy ahmed amin"
                , "ramyahmedamin@gmail.com", "ramy123"));
        doctors.add(new doctor("Ali", 124, "Ali abdelrhime ali"
                , "aliabdelrhimeali@gmail.com", "ali124"));
        doctors.add(new doctor("Hussin", 126, "Hussin Muhmmoud taha"
                , "hussinmuhmmoudtaha@gmail.com", "ramy126"));
        doctors.add(new doctor("Hussn", 126, "Hussn alsyed Akram"
                , "hussnalsyedakram@gmail.com", "ramy126"));
        doctors.add(new doctor("Mohmmed", 129, "Mohmmed Youssef Ahmed"
                , "mohmmedyoussefahmed@gmail.com", "Mohmmed129"));
        doctors.add(new doctor("Omer", 122, "Omer Hossam hussin"
                , "omerhossamhussin@gmail.com", "Omer122"));
        doctors.add(new doctor("karam", 121, "Karam abdelaziz hussn"
                , "karamabdelazizhussn@gmail.com", "karam121"));


        students.add(new student("Ali", 231, "Ali mohamed ahmed"
                , "alimohamedahmed@gmail.com", "ali231"));
        students.add(new student("Alaa", 233, "Alaa alsyed mohmed"
                , "alaaalsyedmohmed@gmail.com", "alaa233"));
        students.add(new student("karim", 222, "Karim athman hussin"
                , "karimathmanhussin@gmail.com", "karim222"));
        students.add(new student("Mostafa", 234, "Mostafa Mosa medhat "
                , "mostafamosamedhat@gmail.com", "mostafa234"));
        students.add(new student("islam", 235, "Islam Muntaser Ali"
                , "islammuntaserali@gmail.com", "islam235"));
        students.add(new student("Eid", 252, "Eid saad ahmed"
                , "eidsaadahmed@gmail.com", "Eid252"));
        students.add(new student("kamal", 243, "kamal mohamed ahmed"
                , "kamalmohamedahmed@gmail.com", "kamal243"));
        students.add(new student("mohamed", 244, "mohamed muhmoud ahmed"
                , "mohamed muhmoud ahmed@gmail.com", "mohamed244"));
        students.add(new student("Mohammed", 251, "Mohammed Ali kamel"
                , "mohammedalikamel@gmail.com", "Mohammed251"));
        students.add(new student("muhmmoud", 241, "muhmmoud hussin ali"
                , "muhmmoudhussinali@gmail.com", "muhmmoud241"));
        students.add(new student("Taha", 271, "Taha taraq ahmed"
                , "tahataraqahmed@gmail.com", "taha271"));


        courses.get(0).getStudents().add(students.get(0));
        courses.get(0).getStudents().add(students.get(1));
        courses.get(0).getStudents().add(students.get(2));

        courses.get(1).getStudents().add(students.get(0));
        courses.get(1).getStudents().add(students.get(1));
        courses.get(1).getStudents().add(students.get(2));

        courses.get(2).getStudents().add(students.get(0));
        courses.get(2).getStudents().add(students.get(1));
        courses.get(2).getStudents().add(students.get(2));

        students.get(0).getRegisterCourses().add(courses.get(0));
        students.get(0).getRegisterCourses().add(courses.get(1));
        students.get(0).getRegisterCourses().add(courses.get(2));

        students.get(1).getRegisterCourses().add(courses.get(0));
        students.get(1).getRegisterCourses().add(courses.get(1));
        students.get(1).getRegisterCourses().add(courses.get(2));

        students.get(2).getRegisterCourses().add(courses.get(0));
        students.get(2).getRegisterCourses().add(courses.get(1));
        students.get(2).getRegisterCourses().add(courses.get(2));

        courses.get(0).getStudents().get(0).getAssignments().add(new Assignment("", "Define Algorithm", 40));
        courses.get(0).getAssignments().add(new Assignment("", "Define Algorithm", 40));
        doctors.get(0).getTeachingCourse().add(courses.get(0));
    }

    /*method to get the list of student.*/
    static ArrayList<student> getStudents() {
        return students;
    }

    /*method to get the list of doctor. */
    static ArrayList<doctor> getDoctors() {
        return doctors;
    }

    /*method to get the list of course. */
    static ArrayList<course> getCourses() {
        return courses;
    }

    /*
     * method to return the position of student in the array of student.
     *
     * (@param) username refer to the username of the student.
     * (@param) password refer to the password of the student.
     *
     * return -1 if doesn't exist .
     * */
    static int getStudent(String username, String password) {
        // ArrayList<student> s = Data.students;
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getUsername().equals(username) && students.get(i).getPassword().equals(password)) {
                return i;
            }
        }
        return -1;
    }

    /*
     * method to return the position of doctor in the array of doctor.
     *
     * (@param) username refer to the username of the doctor.
     * (@param) password refer to the password of the doctor.
     *
     * return -1 if doesn't exist .
     * */
    static int getDoctor(String username, String password) {
        for (int i = 0; i < doctors.size(); i++) {
            if (doctors.get(i).getUsername().equals(username) && doctors.get(i).getPassword().equals(password)) {
                return i;
            }
        }
        return -1;
    }

    static boolean isRegistered(String nameOfCourse, String nameOfStudent) {
        boolean register = false;
        course course = getCourse(nameOfCourse);
        for (int i = 0; i < Objects.requireNonNull(course).getStudents().size(); i++) {
            if (course.getStudents().get(i).getUsername().equals(nameOfStudent)) {
                register = true;
                break;
            }
        }
        return register;
    }

    /*
     * method to hold the processing of the program
     * for 1.5 second to display message,..etc
     * */
    static void sleep() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static course getCourse(String nameOfCourse) {
        for (int i = 0; i < getCourses().size(); i++) {
            if (getCourses().get(i).getName().equals(nameOfCourse)) {
                return getCourses().get(i);
            }
        }
        return null;
    }
}
