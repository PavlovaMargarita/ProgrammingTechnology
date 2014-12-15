package param;

import java.util.ResourceBundle;

public class Params {

    public static ResourceBundle bundle = ResourceBundle.getBundle("database");
    public static final String METHOD = "method";

    public static final String AUTHORIZATION = "authorization";

    public static final String LOGIN_NAME = "login";
    public static final String LOGIN_PASSWORD = "password";
    public static final String LOGOUT="logout";

    public static final String STUDENT_GET_MARKS = "studentGetMark";
    public static final String STUDENT_GET_COURSES = "studentGetCourses";
    public static final String STUDENT_DELETE_COURSE = "studentDeleteCourse";
    public static final String STUDENT_EDIT_COURSES = "studentEditCourses";
    public static final String TEACHER_GET_COURSE = "teacherGetCourse";
    public static final String TEACHER_GET_STUDENT_BY_COURSE_ID = "teacherGetStudentByCourseId";

    public static final String TEACHER_SET_MARKS = "teacherSetMarks";

    public static final String TEACHER_EDIT_COURSES = "teacherEditCourses";
    public static final String TEACHER_SPECIFY_COURSE = "teacherSpecifyCourse";
    public static final String TEACHER_SAVE_COURSE = "teacherSaveCourse";

    public static final String STUDENT_SAVE_COURSE = "studentSaveCourse";
    public static final String INDEX_JSP = "index.jsp";
    public static final String TEACHER_LIST_STUDENTS_TO_SET_MARKS = "teacherGetStudentListToSetMark";
    public static final String TEACHER_SAVE_MARKS="saveMarks";


    public static final String STUDENT_COURSE_LIST_JSP = "studentCourseList.jsp";
    public static final String STUDENT_EDIT_COURSE_JSP = "studentEditCourse.jsp";
    public static final String STUDENT_MARKS_JSP = "studentMarks.jsp";

    public static final String TEACHER_STUDENT_LIST_JSP = "teacherStudentList.jsp";
    public static final String TEACHER_COURSE_LIST_JSP = "teacherCourseList.jsp";

    public static final String TEACHER_COURSES_JSP = "teacherCourses.jsp";
    public static final String TEACHER_LIST_STUDENTS_TO_SET_MARKS_JSP = "teacherGetStudentListToSetMark.jsp";
    public static final String LOGIN_JSP="login.jsp";


    public static final String TEACHER_SPECIFY_COURSE_JSP = "teacherSpecifyCourse.jsp";
    public static final String TEACHER_EDIT_COURSE_JSP = "teacherEditCourse.jsp";

}
