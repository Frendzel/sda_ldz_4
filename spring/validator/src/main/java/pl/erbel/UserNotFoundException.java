package pl.erbel;

public class UserNotFoundException extends Exception {

    private static String ERROR = "USER HAS NOT BEEN FOUND:";

    public UserNotFoundException(String login) {
        super(ERROR + login);
    }

}
