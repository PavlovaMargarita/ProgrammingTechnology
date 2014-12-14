package controller.command;

import dao.impl.StudentDaoImpl;
import model.Course;
import model.Student;
import param.Params;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Margarita on 09.12.2014.
 */
public class StudentEditCourses implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int userId = Integer.parseInt(request.getSession(true).getAttribute("idUser").toString());
        Student student = StudentDaoImpl.getInstance().getCourses(userId);
        String param = request.getParameter("student_course_param");
        request.setAttribute("student", student);
        request.setAttribute("student_course_param", param);
        List<Course> courseList = StudentDaoImpl.getInstance().getAllCourses();
        request.setAttribute("fullCourseList", courseList);
        return Params.STUDENT_EDIT_COURSE_JSP;
    }

}
