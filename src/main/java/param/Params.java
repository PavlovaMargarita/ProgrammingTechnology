package param;

import java.util.ResourceBundle;

/**
 * Created by marharyta.pavlova on 08-Dec-14.
 */
public class Params {

    public static ResourceBundle bundle = ResourceBundle.getBundle("database");
    public static final String METHOD = "method";
    public static final String GET_STUDENT_MARKS = "getStudentMarks";
    public static final String AUTHORIZATION = "authorization";

    public static final String LOGIN_NAME = "login";
    public static final String LOGIN_PASSWORD = "password";
}
