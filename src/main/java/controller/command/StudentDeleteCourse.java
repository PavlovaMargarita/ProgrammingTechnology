package controller.command;

import dao.impl.StudentDaoImpl;
import model.Student;
import param.Params;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class StudentDeleteCourse implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int userId = Integer.parseInt(request.getSession(true).getAttribute("idUser").toString());
        StudentDaoImpl.getInstance().deleteCourse(userId);
        Student student = StudentDaoImpl.getInstance().getCourses(userId);
        request.setAttribute("student", student);
        return Params.STUDENT_COURSE_LIST_JSP;
    }
}
