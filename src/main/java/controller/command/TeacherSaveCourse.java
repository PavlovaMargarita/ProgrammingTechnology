package controller.command;

import dao.impl.TeacherDaoImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class TeacherSaveCourse implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int userId = Integer.parseInt(request.getSession(true).getAttribute("idUser").toString());
        int courseId = Integer.parseInt(request.getParameter("course"));
        if("delete".equals(request.getParameter("teacher_course_param"))) {
            TeacherDaoImpl.getInstance().deleteCourse(userId, courseId);
        }
        if("create".equals(request.getParameter("teacher_course_param"))) {
            TeacherDaoImpl.getInstance().addCourse(userId, courseId);
        }
            return null;
    }
}
