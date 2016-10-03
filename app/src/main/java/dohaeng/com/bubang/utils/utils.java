package dohaeng.com.bubang.utils;

/**
 * Created by gmkwark on 16. 8. 3..
 */
public class utils {
    public static boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }


    public static boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }
}
