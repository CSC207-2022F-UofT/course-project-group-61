package userlogin;

/* Data structure for the request of a call to the login interactor. */
public class UserLoginRequestModel {

    private String username;
    private String password;

    public UserLoginRequestModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
