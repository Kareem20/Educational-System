package sample;

class Assignment {

    /* @Param (info) refer to the information of this assignment.*/
    private final String info;

    /*@Param (question) refer to the question of the assignment.*/
    private final String question;

    /*@Param (fullGrade) refer to the fullGrade of the assignment.*/
    private final int fullGrade;

    /*@Param (isSubmitted) refer if assignment is submitted or not.*/
    private boolean isSubmitted;

    /*@Param (studentGrade) refer to the grade of the student.*/
    private int studentGrade;

    /*@Param (question) refer to the answer of the student.*/
    private String studentAnswer;

    Assignment(String info, String question, int fullGrade) {
        this.info = info;
        this.question = question;
        this.fullGrade = fullGrade;
        this.studentAnswer = "";
        this.isSubmitted = false;
        this.studentGrade = -1;
    }


    /*
     * method to get the information of assignment
     *
     * return (String).
     * */
    String getInfo() {
        return info;
    }

    /*
     * method to get the question of assignment.
     *
     * return (String).
     * */
    String getQuestion() {
        return question;
    }

    /*
     * method to get the student's answer.
     *
     * return (String).
     * */
    String getStudentAnswer() {
        return studentAnswer;
    }

    /*
     * method to make doctor set the grade of student.
     */
    void setStudentGrade(int studentGrade) {
        this.studentGrade = studentGrade;
    }

    /*
     * method to make student set answer of assignment.
     */
    void setStudentAnswer(String studentAnswer) {
        this.studentAnswer = studentAnswer;
        isSubmitted = true;
    }

    /*
     * method to get the assignment's full grade.
     *
     * return (int).
     * */
    int getFullGrade() {
        return fullGrade;
    }

    /*
     * method to get if student submit the
     * answer of not.
     *
     * return (boolean).
     * */
    boolean isSubmitted() {
        return isSubmitted;
    }

    /*
     * method to get the student's grade.
     *
     * return (int).
     * */
    int getStudentGrade() {
        return studentGrade;
    }

    /*
     * method to get if the answer of student
     * is graded or not.
     *
     * return (boolean).
     * */
    boolean isGrade() {
        return (studentGrade != -1);
    }

}
