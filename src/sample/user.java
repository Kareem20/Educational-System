package sample;

class user {

    //the username of user.
    private final String username;
    //the id of the user.
    private final int ID;
    //the full name of the user.
    private final String fullName;
    //the e-mail of user.
    private final String email;
    //the password of user.
    private final String password;


    user(String username, int ID, String fullName, String email, String password) {
        this.username = username;
        this.ID = ID;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
    }

    /*method to get the user name of user.*/
    String getUsername() {
        return username;
    }

    /*method to get the password of user.*/
    String getPassword() {
        return password;
    }

    /*method to get the id of user.*/
    int getID() {
        return ID;
    }

    /*method to get the full name of user.*/
    String getFullName() {
        return fullName;
    }

    /*method to get the email of user.*/
    String getEmail() {
        return email;
    }

}
