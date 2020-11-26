package sample;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

class DoctorInterface {

    private Scanner scanner = new Scanner(System.in);
    //position of doctor in array.
    private int doctorPosition = -1;

    /*
     * log in method to make Doctor enter username
     * and password and make sure of it.
     * */
    void logIn() {
        System.out.println("Enter your username");
        scanner = new Scanner(System.in);
        String doctorUsername = scanner.nextLine();
        System.out.println("Enter your password");
        String doctorPassword = scanner.nextLine();
        int num = Data.getDoctor(doctorUsername, doctorPassword);
        if (num == -1) {
            System.out.println("username or password is wrong \n Please Enter the right password or user name");
            logIn();
        } else {
            doctorPosition = num;
            System.out.println("Welcome Dr/ " + Data.getDoctors().get(doctorPosition).getFullName());
            Data.sleep();
            line();
            optionMenu();
        }

    }

    /*
     * method to show user (doctor) the options
     * and make him to choose one of them.
     *  */
    private void optionMenu() {
        System.out.println("Enter the number of option that you want to do.");
        System.out.println(" Please make a choice : " +
                "\n" + "1- Create new Course." +
                "\n" + "2- View all available Course." +
                "\n" + "3- View Your teaching Course." +
                "\n" + "4- View Specific Course." +
                "\n" + "5- Loge out.");
        int option;
        try {
            scanner = new Scanner(System.in);
            option = scanner.nextInt();
            if (option == 1) {
                createCourse();
                line();
                optionMenu();
            } else if (option == 2) {
                viewAllCourses();
                line();
                optionMenu();
            } else if (option == 3) {
                viewTeachingCourses();
                line();
                optionMenu();
            } else if (option == 4) {
                viewSpecificCourse();
                line();
                optionMenu();
            } else if (option == 5) {
                end();
            } else {
                System.out.println("Please Enter suitable number");
                Data.sleep();
                optionMenu();
            }
        } catch (InputMismatchException e) {
            System.out.println("Please Enter suitable number");
            Data.sleep();
            optionMenu();
        }

    }

    /*
     *method to view specific data course.
     * */
    private void viewSpecificCourse() {
        viewAllCourses();
        //try-catch to make sure of the validation of input.
        try {
            System.out.println("Enter the name of course that you want.");
            scanner = new Scanner(System.in);
            String courseName = scanner.nextLine();
            //get the course that has the input name.
            course course = Data.getCourse(courseName);
            //if there is no course has this name view side menu.
            if (course == null) {
                System.out.println("there is no course that has this name" +
                        "\n" + "to enter different name enter 1" +
                        "\n" + "to back main menu enter 2");
                int result = sideOption();
                if (result == 1) {
                    viewSpecificCourse();
                    return;
                }
                return;
            }
            System.out.println("Course Name:  " + course.getName() +
                    "\n" + "        " + "Course Code:  " + course.getCode() +
                    "\n" + "        " + "Doctor who teach this course:  " +
                    (course.getDoctorName() != null ? course.getDoctorName() : "Not Yet") +
                    "\n" + "        " + "Number of register student:  " + course.getStudents().size() +
                    "\n" + "        " + "Number of Assignments:  " + course.getAssignments().size());
            viewCourseOption(courseName);
        } catch (InputMismatchException e) {
            System.out.println("Please,Enter suitable input.");
            Data.sleep();
            viewSpecificCourse();
        }
    }

    /*
     * method to view the option that user can make
     * in specific course.
     *
     * (@param) courseName refer to the name of course.
     * */
    private void viewCourseOption(String courseName) {
        miniLine();
        System.out.println("1- List of assignment." +
                "\n" + "2- Create Assignment." +
                "\n" + "3- view the information of registered student" +
                "\n" + "4- Show grades report." +
                "\n" + "5- Set grades for assignments" +
                "\n" + "6- back");
        try {
            scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            if (option == 1) {
                viewListOfAssignment(courseName);
                miniLine();
                viewCourseOption(courseName);
            } else if (option == 2) {
                createAssignment(courseName);
                miniLine();
                viewCourseOption(courseName);
            } else if (option == 3) {
                viewInfoStudent(courseName);
                miniLine();
                viewCourseOption(courseName);
            } else if (option == 4) {
                showGradesReport(courseName);
                miniLine();
                viewCourseOption(courseName);
            } else {
                return;
            }
            System.out.println("Please,Enter suitable input.");
            Data.sleep();
            viewCourseOption(courseName);

        } catch (InputMismatchException e) {
            System.out.println("Please,Enter suitable input.");
            Data.sleep();
            viewCourseOption(courseName);
        }
    }

    /*
     * method to create new assignment in specific course.
     *
     * (@param) courseName refer to the name of course that
     * user want to create assignment in it.
     * */
    private void createAssignment(String courseName) {
        //initialize the information and question of assignment.
        String info, question;
        //initialize the full grade of assignment.
        int fullGrade;
        //try-catch to make sure the input is valid.
        try {
            scanner = new Scanner(System.in);
            System.out.println("Enter the Information of assignment.");
            info = scanner.nextLine();
            System.out.println("Enter the question of assignment.");
            question = scanner.nextLine();
            System.out.println("Enter the full grade of assignment.");
            fullGrade = scanner.nextInt();
            //save the data in the database in specific course by it's name.
            Objects.requireNonNull(Data.getCourse(courseName)).getAssignments().add(new Assignment(info, question, fullGrade));
            System.out.println("Creation Successfully.");
            miniLine();
            //after the creation ,view the list of new assignment of current course.
            viewListOfAssignment(courseName);
        } catch (InputMismatchException e) {
            System.out.println("Please,Enter the suitable input.");
            Data.sleep();
            createAssignment(courseName);
        }

    }


    /*
     * method to view report about the student grade of all
     *  assignment of specific course by it's name.
     *
     * (@param) courseName refer to the name of course
     * that user want to know the student grade.
     *  */
    private void showGradesReport(String courseName) {
        // get the wanted course.
        course course = Data.getCourse(courseName);
        //check if course has register student or not.
        assert course != null;
        if (course.getStudents().size() != 0) {
            //for loop- to loop in all student in course.
            for (int i = 0; i < course.getStudents().size(); i++) {
                /*
                 * (@param) totalStudentGrades refer to the total grade of student in all assignment in course.
                 * (@param) fullGrades refer to the total of full grades of assignment.
                 * (@param)  numRegistered refer to the number of assignment that student submit solution in it.
                 * */
                int totalStudentGrades = 0, fullGrades = 0, numRegistered = 0;
                //for loop - to loop in all student assignmentto get the information of it.
                for (int j = 0; j < course.getStudents().get(i).getAssignments().size(); j++) {
                    totalStudentGrades += course.getStudents().get(i).getAssignments().get(j).getStudentGrade();
                    fullGrades += course.getStudents().get(i).getAssignments().get(j).getFullGrade();
                    if (course.getStudents().get(i).getAssignments().get(j).isSubmitted()) {
                        numRegistered++;
                    }
                }
                System.out.println((i + 1) + "- " + "Student's name is: " + course.getStudents().get(i).getFullName() +
                        "\n" + "        " + "number of submitted is: " + numRegistered +
                        "\n" + "        " + "Total grades: " + totalStudentGrades + "/" + fullGrades);
            }
        } else {
            System.out.println("This course hasn't student register yet.");
            Data.sleep();
        }
    }

    /*
     * method to view the all registered student in specific course.
     * if there is no student register in course ,print message.
     *
     * (@param) courseName refer to the specific course we view it.
     *
     * */
    private void viewInfoStudent(String courseName) {
        //get the course by it's name.
        course course = Data.getCourse(courseName);
        assert course != null;
        if (course.getStudents().size() != 0) {
            for (int i = 0; i < course.getStudents().size(); i++) {
                System.out.println((i + 1) + "- " + "Student full name: " + course.getStudents().get(i).getFullName()
                        + "        " + "Student ID: " + course.getStudents().get(i).getID()
                        + "        " + "Student E-mail: " + course.getStudents().get(i).getEmail());
            }
        } else {
            Data.sleep();
            System.out.println("there is no student register in this course yet.");
        }
    }


    /*
     * method to view the all assignment in specific course.
     * if there is no assignment in course ,print message.
     *
     * (@param) courseName refer to the specific course we view it.
     *
     * */
    private void viewListOfAssignment(String courseName) {
        //get the course by it's name.
        course course = Data.getCourse(courseName);
        assert course != null;
        if (course.getAssignments().size() != 0) {
            for (int i = 0; i < course.getAssignments().size(); i++) {
                System.out.println((i + 1) + "- " + "Information of assignment: " + course.getAssignments().get(i).getInfo() +
                        "\n" + "        " + "The Question: " + course.getAssignments().get(i).getQuestion() +
                        "\n" + "        " + "The full grade:  " + course.getAssignments().get(i).getFullGrade());
            }
        } else {
            // if not print this message.
            System.out.println("This course doesn't have assignments yet");
            Data.sleep();
        }
    }

    /*
     * this method show side menu to
     * repeat the operation or back to previous menu.
     *
     * return 1 for repeat, 2 for back. */
    private int sideOption() {
        int r = -1;
        try {
            scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            if (option == 1) {
                return 1;
            } else if (option == 2) {
                return 2;
            } else {
                System.out.println("Please,Enter suitable input.");
                Data.sleep();
                sideOption();
            }
        } catch (InputMismatchException e) {
            System.out.println("Please,Enter suitable input.");
            Data.sleep();
            sideOption();
        }
        return r;
    }

    /*
     * method to create a new course by it's name , code and doctor.
     * */
    private void createCourse() {
        String name, code, doctorName;
        //try-catch to make sure the input is valid.
        try {
            scanner = new Scanner(System.in);
            //first input the name of course.
            System.out.println("Enter the name of course");
            name = scanner.nextLine();
            // check the validation of course.
            int c = checkName(name);
            if (c == 1) {
                createCourse();
                return;
            } else if (c == 2) {
                return;
            }
            System.out.println("Enter the code of course");
            code = scanner.nextLine();
            System.out.println("Enter the name of doctor" + "\n" + "Note:if there has no doctor selected,enter not yet.");
            doctorName = scanner.nextLine();
            if (doctorName.equals("not yet")) {
                doctorName = null;
            }
            //set the created course in the database.
            Data.getCourses().add(new course(name, code, doctorName));
            System.out.println("Creation Done..");

        } catch (InputMismatchException e) {
            System.out.println("Please Enter suitable Text.");
            Data.sleep();
            createCourse();
        }
    }

    /*
     * method to check the name of input course and if
     *  it exist in database or not.
     *
     * (@param) courseName refer to the name of input course.
     *
     * return (integer) 0 if name doesn't exist,
     * 1 if the name exist and User want to enter different name ,
     * 2 if the name exist and User want to back to main options.
     * */
    private int checkName(String courseName) {
        int r = -1;
        //for loop to check of Existing of name in database.
        for (int i = 0; i < Data.getCourses().size(); i++) {
            if (Data.getCourses().get(i).getName().equals(courseName)) {
                r = 0;
                break;
            }
        }
        if (r == 0) {
            System.out.println("The Entered name is existing in database.");
            miniLine();
            System.out.println("Select Option: " +
                    "\n" + "1- Enter different name." +
                    "\n" + "2- Back.");
            try {
                int option = scanner.nextInt();
                if (option == 1) {
                    return 1;
                } else if (option == 2) {
                    return 2;
                } else {
                    System.out.println("Please Enter suitable number");
                    Data.sleep();
                    checkName(courseName);
                }
            } catch (InputMismatchException e) {
                System.out.println("Please Enter suitable number");
                Data.sleep();
                checkName(courseName);
            }
        }
        return r;
    }

    /*
     * method to print the All courses and it's data.
     * */
    private void viewAllCourses() {
        if (Data.getCourses().size() != 0) {
            int a = 1;
            //for loop to print all course's data.
            for (sample.course course : Data.getCourses()) {
                System.out.println(a + "- " + "Course Name:  " + course.getName() +
                        "\n" + "        " + "Course Code:  " + course.getCode() +
                        "\n" + "        " + "Doctor who teach this course:  " +
                        (course.getDoctorName() != null ? course.getDoctorName() : "Not Yet") +
                        "\n" + "        " + "Number of register student:  " + course.getStudents().size() +
                        "\n" + "        " + "Number of Assignments:  " + course.getAssignments().size());
                a += 1;
            }
        }
    }

    /*
     * method the view the courses that the
     * user(doctor) is teaching it.
     *
     * if there is not courses he teaching it , show message.
     * */
    private void viewTeachingCourses() {
        // the name of doctor.
        String currentDoctorName = Data.getDoctors().get(doctorPosition).getUsername();
        int a = 1;
        for (int i = 0; i < Data.getCourses().size(); i++) {
            if (Data.getCourses().get(i).getDoctorName().equals(currentDoctorName)) {
                //for loop to print all course's data.
                System.out.println(a + "- " + "Course Name:  " + Data.getCourses().get(i).getName() +
                        "\n" + "        " + "Course Code:  " + Data.getCourses().get(i).getCode() +
                        "\n" + "        " + "Number of register student:  " + Data.getCourses().get(i).getStudents().size() +
                        "\n" + "        " + "Number of Assignments:  " + Data.getCourses().get(i).getAssignments().size());
                a += 1;
            }
        }
        if (a == 1) {
            Data.sleep();
            System.out.println("You haven't teaching any course yet.");
        }

    }

    /*method to print the message and end the main option an*/
    private void end() {
        System.out.println("See you later..");
    }

    /*method to print line. */
    private void line() {
        System.out.println("------------------------------");
    }

    /*method to print small line. */
    private void miniLine() {
        System.out.println("------------");
    }
}
