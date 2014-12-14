package controller.command;

import dao.impl.StudentDaoImpl;
import model.Student;
import param.Params;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class StudentGetCourses implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Student student = StudentDaoImpl.getInstance().getCourses();
        request.setAttribute("student", student);
        return Params.STUDENT_COURSE_LIST_JSP;
    }

}
