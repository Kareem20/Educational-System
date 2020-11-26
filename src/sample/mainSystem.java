package sample;

import java.util.InputMismatchException;
import java.util.Scanner;

public class mainSystem {

    //Scanner for reading the user Input.
    private Scanner scanner;
    //object of doctorInterface to run the doctor options.
    private final DoctorInterface doctorInterface;
    //object of StudentInterface to run the student options.
    private final StudentInterface studentInterface;

    //constructor will be called when we create object of it.
    mainSystem() {
        //initialize scanner
        scanner = new Scanner(System.in);
        //initialize Data.
        //Data to fill the arrays of our dummy data.
        Data data = new Data();
        //initialize doctorInterface object.
        doctorInterface = new DoctorInterface();
        //initialize studentInterface object.
        studentInterface = new StudentInterface();
    }

    /*
     * determined how is log in
     * student or doctor.
     *
     * */
    void option() {
        int option = getOption();
        if (option == 1) {
            doctorInterface.logIn();
            option();
        } else if (option == 2) {
            studentInterface.logIn();
            option();
        } else if (option == -1) {
            option();
        } else {
            System.out.println("See you late.");
        }
    }

    /*
     * method to  make user make choose doctor or student.
     *
     * return -1 if there is error in input of user.
     * */
    private int getOption() {
        scanner = new Scanner(System.in);
        int option = -1;
        try {
            System.out.print("Are you ? \n1-Doctor \n2-Student\n3-End\n");
            do {
                System.out.print("Please Enter valid choice:\n");
                option = scanner.nextInt();
            } while (option < 1 || option > 3);
            return option;
        } catch (InputMismatchException e) {
            System.out.println("Please Enter suitable number.");
            Data.sleep();
        }
        return option;
    }


}

