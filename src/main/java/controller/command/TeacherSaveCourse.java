package controller.command;

import dao.impl.TeacherDaoImpl;
import model.Course;
import param.Params;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class TeacherSaveCourse implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int userId = Integer.parseInt(request.getSession(true).getAttribute("idUser").toString());
        int courseId = Integer.parseInt(request.getParameter("course"));
        if ("delete".equals(request.getParameter("teacher_course_param"))) {
            TeacherDaoImpl.getInstance().deleteCourse(userId, courseId);
        }
        if ("create".equals(request.getParameter("teacher_course_param"))) {
            TeacherDaoImpl.getInstance().addCourse(userId, courseId);
        }
        List<Course> courseList = TeacherDaoImpl.getInstance().getCourses(userId);
        request.setAttribute("courseList", courseList);
        return Params.TEACHER_SPECIFY_COURSE_JSP;
    }
}
