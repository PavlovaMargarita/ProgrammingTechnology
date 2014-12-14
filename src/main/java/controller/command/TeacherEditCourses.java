package controller.command;

import dao.impl.TeacherDaoImpl;
import param.Params;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.*;

public class TeacherEditCourses implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String param = request.getParameter("teacher_course_param");
        if("create".equals(param)){
            List course = TeacherDaoImpl.getInstance().getFreeCourse();
            request.setAttribute("courseList", course);
        }
        if("delete".equals(param)){
            int userId = Integer.parseInt(request.getSession(true).getAttribute("idUser").toString());
            List course = TeacherDaoImpl.getInstance().getCourses(userId);
            request.setAttribute("courseList", course);
        }
        request.setAttribute("teacher_course_param", param);
        return Params.TEACHER_EDIT_COURSE_JSP;
    }

}
