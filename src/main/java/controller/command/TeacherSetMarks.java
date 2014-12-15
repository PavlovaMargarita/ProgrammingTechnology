package controller.command;

import dao.impl.TeacherDaoImpl;
import model.Course;
import param.Params;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class TeacherSetMarks implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        List<Course> courseList = TeacherDaoImpl.getInstance().getCourses();
        request.setAttribute("courseList", courseList);
        return Params.TEACHER_COURSES_JSP;
    }
}
