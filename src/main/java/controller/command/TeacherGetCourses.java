package controller.command;

import dao.impl.TeacherDaoImpl;
import model.Course;
import param.Params;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Margarita on 08.12.2014.
 */
public class TeacherGetCourses implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int userId = Integer.parseInt(request.getSession(true).getAttribute("idUser").toString());
        List<Course> courseList = TeacherDaoImpl.getInstance().getCourses(userId);
        request.setAttribute("courseList", courseList);
        return Params.TEACHER_COURSE_LIST_JSP;
    }
}
