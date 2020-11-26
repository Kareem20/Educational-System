package sample;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

class StudentInterface {

    private Scanner scanner = new Scanner(System.in);
    //position of student in the array.
    private int studentPosition = -1;


    /*
     * log in method to make Student enter username
     * and password and make sure of it.
     * */
    void logIn() {
        System.out.println("Enter your username.");
        scanner = new Scanner(System.in);
        String studentUsername = scanner.nextLine();
        System.out.println("Enter your password.");
        String studentPassword = scanner.nextLine();
        int num = Data.getStudent(studentUsername, studentPassword);
        if (num == -1) {
            System.out.println("username or password is wrong \n Please Enter the right password or user name");
            logIn();
        } else {
            studentPosition = num;
            System.out.println("Welcome " + Data.getStudents().get(studentPosition).getFullName());
            Data.sleep();
            line();
            optionMenu();
        }

    }


    /*
     * method to show user (student) the options
     * and make him to choose one of them.
     *  */
    private void optionMenu() {
        System.out.println("Enter the number of option that you want to do.");
        System.out.println("Please make a choice :" +
                "\n" + "1- Register in specific course." +
                "\n" + "2- View List of your Courses." +
                "\n" + "3 - View List of all available Course." +
                "\n" + "4 - View Specific Course." +
                "\n" + "5 - loge out");
        int option;
        //try-catch : to make sure the type of input is integer.
        try {
            scanner = new Scanner(System.in);
            option = scanner.nextInt();
            if (option == 1) {
                registerOnCourse();
                line();
                optionMenu();
            } else if (option == 2) {
                viewStudentCourses();
                line();
                optionMenu();
            } else if (option == 3) {
                viewAllCourses();
                line();
                optionMenu();
            } else if (option == 4) {
                String n = viewSpecificCourse();
                courseOption(n);
                line();
            } else if (option == 5) {
                end();
            } else {
                System.out.println("Please Enter suitable number");
                optionMenu();
            }
        } catch (InputMismatchException e) {
            System.out.println("Please Enter suitable number");
            optionMenu();
        }
    }

    /*method to print the message and end the main option an*/
    private void end() {
        System.out.println("See you later..");
    }

    /*
     *first print the course that student did not register in.
     *
     * method to make the process of Course Registration.
     * */
    private void registerOnCourse() {
       /* ArrayList<course> c = Data.getCourses();
        ArrayList<student> s = Data.getStudents();*/
        //print the courses
        int size = viewUnRegisterStudentCourses().size();
        if (size != 0) {
            miniLine();
            System.out.println("Enter the name of course that you want to register in.");
            //try-catch to make sure that student enter String.
            try {
                //input the name of course
                scanner = new Scanner(System.in);
                String nameOfCourse = scanner.nextLine();
                boolean exist = false;
                for (int i = 0; i < Data.getCourses().size(); i++) {
                    if (Data.getCourses().get(i).getName().equals(nameOfCourse)) {
                        //save the registration int courses data and student data respectively.
                        Data.getCourses().get(i).getStudents().add(Data.getStudents().get(studentPosition));
                        Data.getStudents().get(studentPosition).getRegisterCourses().add(Data.getCourse(nameOfCourse));
                        exist = true;
                    }
                }
                //if student enter number don't exist in course.
                if (!exist) {
                    System.out.println("The name does not exist");
                    registerOnCourse();
                } else {
                    System.out.println("Registered Done..");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please Enter suitable number");
                registerOnCourse();
            }
        } else {
            System.out.println("You register in all Available Courses.");
        }

    }

    /*
     * method to print Course that student did not
     * register in it.
     *
     * return Array list of courses.
     * */
    private ArrayList<course> viewUnRegisterStudentCourses() {
        int a = 1;
        boolean exist;
        //Array list or result courses
        ArrayList<course> resultCourses = new ArrayList<>();
        //for loop on all courses in system .
        for (sample.course course : Data.getCourses()) {
            exist = false;
            //for loop in student courses.
            // check if course exist in student courses list.
            for (course studentCourse : Data.getStudents().get(studentPosition).getRegisterCourses()) {
                if (course.getName().equals(studentCourse.getName())) {
                    exist = true;
                    break;
                }
            }
            // if the course does not exist ,add it in courses result and print it.
            if (!exist) {
                resultCourses.add(course);
                System.out.println(a + "- " + "Course Name:  " + course.getName() +
                        "\n" + "        " + "Course Code:  " + course.getCode() +
                        "\n" + "        " + "Doctor who teach this course:  " + course.getDoctorName() +
                        "\n" + "        " + "Number of register student:  " + course.getStudents().size() +
                        "\n" + "        " + "Number of Assignments:  " + course.getAssignments().size());
                a += 1;
            }
        }
        return resultCourses;
    }

    private void viewStudentCourses() {
        int a = 1;
        boolean exist;
        //for loop on all courses in system .
        for (sample.course course : Data.getCourses()) {
            exist = false;
            //for loop in student courses.
            // check if course exist in student courses list.
            for (course studentCourse : Data.getStudents().get(studentPosition).getRegisterCourses()) {
                if (course.getName().equals(studentCourse.getName())) {
                    exist = true;
                    break;
                }
            }
            // if the course does not exist ,add it in courses result and print it.
            if (exist) {
                System.out.println(a + "- " + "Course Name:  " + course.getName() +
                        "\n" + "        " + "Course Code:  " + course.getCode() +
                        "\n" + "        " + "Doctor who teach this course:  " + course.getDoctorName() +
                        "\n" + "        " + "Number of register student:  " + course.getStudents().size() +
                        "\n" + "        " + "Number of Assignments:  " + course.getAssignments().size());
                a += 1;
            }
        }
    }

    /*
     * method to  print specific course data.
     * it print all of courses first and make user enter the name
     * of course and print All it's data.
     * */
    private String viewSpecificCourse() {
        String nameOfCourse = null;
        //try-catch to make sure that student enter integer.
        try {
            //print all Course's data.
            viewAllCourses();
            miniLine();
            System.out.println("Enter the name of course that you want to view.");
            //make user input the name of course
            scanner = new Scanner(System.in);
            nameOfCourse = scanner.nextLine();
            // get all course's data.
            course course = Data.getCourse(nameOfCourse);
            //check the name of course exist in course or not.
            assert course != null;
            if (course.getName().equals(nameOfCourse)) {
                System.out.println("Course Name:  " + course.getName() +
                        "\n" + "        " + "Course Code:  " + course.getCode() +
                        "\n" + "        " + "Doctor who teach this course:  " + course.getDoctorName() +
                        "\n" + "        " + "Number of register student:  " + course.getStudents().size() +
                        "\n" + "        " + "Number of Assignments:  " + course.getAssignments().size() +
                        "\n" + "        " + "Registered :  " +
                        (Data.isRegistered(course.getName(), Data.getStudents().get(studentPosition).getUsername()) ? "Yes" : "No"));
            } else {
                System.out.println("The name of course don't exist" + "\n" + "Please enter suitable name");
                viewSpecificCourse();
            }
        } catch (InputMismatchException e) {
            System.out.println("Please enter suitable number");
            viewSpecificCourse();
        }
        return nameOfCourse;
    }

    /*
     * method to print the All courses and it's data.
     * */
    private void viewAllCourses() {
        int a = 1;
        //for loop to print all course's data.
        for (sample.course course : Data.getCourses()) {
            System.out.println(a + "- " + "Course Name:  " + course.getName() +
                    "\n" + "        " + "Course Code:  " + course.getCode() +
                    "\n" + "        " + "Doctor who teach this course:  " +
                    (course.getDoctorName() != null ? course.getDoctorName() : "Not Yet") +
                    "\n" + "        " + "Number of register student:  " + course.getStudents().size() +
                    "\n" + "        " + "Number of Assignments:  " + course.getAssignments().size() +
                    "\n" + "        " + "Registered :  " +
                    (Data.isRegistered(course.getName(), Data.getStudents().get(studentPosition).getUsername()) ? "Yes" : "No"));
            a += 1;
        }
    }

    /*
     *method to make user choose options to do
     * when he open the course.
     * he can  choose view course's assignments
     * ,view specific assignment , unRegister
     * in the course or back to main option.
     *
     * (@param) courseName refer to the name of course
     * that he want to view it's option.
     * */
    private void courseOption(String courseName) {
        System.out.println("Enter the number of option that you want to do.");
        System.out.println("Please make a choice :" +
                "\n" + "1-View List of Assignments." +
                "\n" + "2- View Specific Assignment." +
                "\n" + "3- UnRegister." +
                "\n" + "4 - Back.");
        //try-catch to make sure that user in input Integer.
        try {
            scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            if (option == 1) {
                viewListOfAssignment(courseName);
                line();
                courseOption(courseName);
            } else if (option == 2) {
                String assignmentName = viewSpecificAssignment(courseName);
                line();
                assignmentOption(courseName, assignmentName);
                line();
                courseOption(courseName);
            } else if (option == 3) {
                unRegister(courseName);
                line();
                courseOption(courseName);
            } else if (option == 4) {
                line();
                optionMenu();
            } else {
                System.out.println("Please Enter suitable Number");
                courseOption(courseName);
            }
        } catch (InputMismatchException e) {
            courseOption(courseName);
        }
    }

    /*
     * method to make the posses of the unRegistration in course.
     *
     * (@param) courseName refer to the name of the course that user
     * want to unRegister in it.
     *  */
    private void unRegister(String courseName) {
        for (int i = 0; i < Data.getStudents().get(studentPosition).getRegisterCourses().size(); i++) {
            if (Data.getStudents().get(studentPosition).getRegisterCourses().get(i).getName().equals(courseName)) {
                Objects.requireNonNull(Data.getCourse(courseName)).getStudents().remove(1);
                Data.getStudents().get(studentPosition).getRegisterCourses().remove(i);
                System.out.println("UnRegister Done..");
                return;
            }
        }
        //if the name does not follow ,show this message.
        System.out.println("You are already unRegister in this course ");
    }

    /*
     * method to view the list of assignment for specific course.
     *
     * (@param) nameOfCourse refer to the name of course that user want to
     * view it's assignment.
     *
     * return the list of assignment.
     * */
    private ArrayList<Assignment> viewListOfAssignment(String nameOfCourse) {
        //get the course by it's name.
        course course = Data.getCourse(nameOfCourse);
        // the result list of assignment.
        ArrayList<Assignment> assignments = new ArrayList<>();
        //check if course has assignment or not
        assert course != null;
        if (course.getAssignments().size() != 0) {
            for (int i = 0; i < course.getAssignments().size(); i++) {
                System.out.println((i + 1) + "- " + "Information of assignment: " + course.getAssignments().get(i).getInfo() +
                        "\n" + "        " + "The Question: " + course.getAssignments().get(i).getQuestion() +
                        "\n" + "        " + "The full grade:  " + course.getAssignments().get(i).getFullGrade() +
                        "\n" + "        " + "Submitted:  " + (course.getAssignments().get(i).isSubmitted() ? "Yes" : "No") +
                        "\n" + "        " + "Your grade:  " +
                        (course.getAssignments().get(i).getStudentGrade() == -1 ? "Not Yet" : course.getAssignments().get(i).getStudentGrade()));
                assignments.add(course.getAssignments().get(i));
            }
        } else {
            // if not print this message.
            System.out.println("This course doesn't have assignments yet");
        }
        return assignments;
    }

    /*
     * method to view specific assignment of specific course by it's name.
     *
     * (@param) nameOfCourse refer to the name of course.
     * */
    private String viewSpecificAssignment(String nameOfCourse) {
        String assignmentInfo = "";
        // first view the list of course's assignments.
        ArrayList<Assignment> assignments = viewListOfAssignment(nameOfCourse);
        miniLine();
        //check the course has assignment.
        if (assignments.size() != 0) {
            // make user print the number of assignment.
            System.out.println("Enter The number of Assignment");
            //try-catch to make sure that user enter integer.
            try {
                scanner = new Scanner(System.in);
                int numAssignment = scanner.nextInt();
                //check the input number is suitable to the assignment list or not.
                if (numAssignment <= assignments.size()) {
                    numAssignment -= 1;
                    System.out.println("The Information of assignment: " + assignments.get(numAssignment).getInfo() +
                            "\n" + "        " + "The Question: " + assignments.get(numAssignment).getQuestion() +
                            "\n" + "        " + "The full grade : " + assignments.get(numAssignment).getFullGrade() +
                            "\n" + "        " + "Your grade: "
                            + (assignments.get(numAssignment).getStudentGrade() == -1 ? "You have not submit answer yet" : assignments.get(numAssignment).getStudentGrade()));
                    assignmentInfo = assignments.get(numAssignment).getInfo();
                } else {
                    // if not , print this message and call this method again.
                    System.out.println("Please enter suitable number");
                    viewSpecificAssignment(nameOfCourse);
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter suitable number");
                viewSpecificAssignment(nameOfCourse);
            }
        } else {
            // if course don't have assignment ,print this message.
            System.out.println("This course doesn't have assignments yet");
        }
        return assignmentInfo;
    }

    /*
     * method to view the chooses in assignments to
     *  make submit or to back to last options.
     *
     * (@param) courseName refer to the name of course that user is stopping on.
     * (@param) assignmentInfo refer to the information of assignment that user is
     * stopping on.
     * */
    private void assignmentOption(String courseName, String assignmentInfo) {
        // message to user to pick option.
        System.out.println("1- Submit solution." +
                "\n" + "2- Back");
        //try-catch to make sure that user enter integer.
        try {
            scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            if (option == 1) {
                submitSolution(courseName, assignmentInfo);
                line();
            } else if (option == 2) {
                line();
                courseOption(courseName);
            } else {
                System.out.println("Please Enter suitable number.");
                assignmentOption(courseName, assignmentInfo);
            }
        } catch (InputMismatchException e) {
            System.out.println("Please Enter suitable number.");
            assignmentOption(courseName, assignmentInfo);
        }
    }

    /*
     * method to make submit solution in assignment,
     * and save it.
     *
     * (@param) courseName refer to the name of course that user is stopping on.
     * (@param) assignmentInfo refer to the information of assignment that user is
     * stopping on.
     * */
    private void submitSolution(String courseName, String assignmentInfo) {
        //the course we are stopping on it.
        course course = Data.getCourse(courseName);
        int a = 0;
        //for loop to find the assignment by it's info.
        for (int i = 0; i < Objects.requireNonNull(course).getAssignments().size(); i++) {
            if (course.getAssignments().get(i).getInfo().equals(assignmentInfo)) {
                System.out.println("The Question: " + course.getAssignments().get(i).getQuestion());
                // save the number of assignment.
                a = i;
                break;
            }
        }
        miniLine();
        //get the solution and make sure of it.
        String solution = checkSolution();
        // save the solution on the assignment.
        Objects.requireNonNull(Data.getCourse(courseName)).getAssignments().get(a).setStudentAnswer(solution);

    }

    /*
     *method to make user enter the solution and make sure of it.
     *
     * return String of solution.
     * */
    private String checkSolution() {
        //message to user to enter the solution.
        System.out.println("Enter Your solution ..");
        String solution = "";
        //try-catch to make sure user enter the solution.
        try {
            scanner = new Scanner(System.in);
            solution = scanner.nextLine();
            // check that user enter the solution.
            if (!solution.equals("")) {
                System.out.println("Your solution is : " + solution +
                        "\n" + "Are you sure for this solution ? 1 for yes--2 for no");
                int option = scanner.nextInt();
                if (option == 1) {
                    return solution;
                } else if (option == 2) {
                    checkSolution();
                } else {
                    System.out.println("Please Enter suitable number.");
                    checkSolution();
                }
            } else {
                System.out.println("Please Enter suitable number.");
                checkSolution();
            }
        } catch (InputMismatchException e) {
            System.out.println("Please Enter suitable number.");
            checkSolution();
        }
        return solution;
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
