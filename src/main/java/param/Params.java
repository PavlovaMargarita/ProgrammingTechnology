package param;

import java.util.ResourceBundle;

/**
 * Created by marharyta.pavlova on 08-Dec-14.
 */
public class Params {

    public static ResourceBundle bundle = ResourceBundle.getBundle("database");
    public static final String METHOD = "method";
    public static final String STUDENT_GET_MARKS = "studentGetMark";
    public static final String STUDENT_DELETE_COURSE = "studentDeleteCourse";
    public static final String STUDENT_SELECT_COURSE = "studentSelectCourse";
    public static final String STUDENT_UPDATE_COURSE = "studentUpdateCourse";
    public static final String TEACHER_GET_COURSE = "teacherGetCourse";
    public static final String TEACHER_GET_STUDENT_BY_COURSE_ID = "teacherGetStudentByCourseId";

}
