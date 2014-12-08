package controller.command;

import dao.impl.StudentDaoImpl;
import model.Student;
import param.Params;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * Created by Margarita on 09.12.2014.
 */
public class StudentEditCourses implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Student student = StudentDaoImpl.getInstance().getCourses();
        String param = request.getParameter("param");
        request.setAttribute("student", student);
        request.setAttribute("param", param);
        return Params.STUDENT_EDIT_COURSE_JSP;
    }

}
