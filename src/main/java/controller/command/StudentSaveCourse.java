package controller.command;

import dao.impl.StudentDaoImpl;
import model.Student;
import param.Params;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.*;

public class StudentSaveCourse implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int userId = Integer.parseInt(request.getSession(true).getAttribute("idUser").toString());
        if("delete".equals(request.getParameter("student_course_param"))){
            StudentDaoImpl.getInstance().deleteCourse(userId);
        } else {
            List mainCourse = new ArrayList(4);
            mainCourse.add(Integer.parseInt(request.getParameter("mainCourse1")));
            mainCourse.add(Integer.parseInt(request.getParameter("mainCourse2")));
            mainCourse.add(Integer.parseInt(request.getParameter("mainCourse3")));
            mainCourse.add(Integer.parseInt(request.getParameter("mainCourse4")));

            List addCourse = new ArrayList(2);
            addCourse.add(Integer.parseInt(request.getParameter("addCourse1")));
            addCourse.add(Integer.parseInt(request.getParameter("addCourse2")));


            if ("create".equals(request.getParameter("student_course_param"))) {
                StudentDaoImpl.getInstance().updateCourses(mainCourse, addCourse, userId);
            }
            if ("edit".equals(request.getParameter("student_course_param"))) {
                StudentDaoImpl.getInstance().updateCourses(mainCourse, addCourse, userId);
            }

        }
        Student student = StudentDaoImpl.getInstance().getCourses(userId);
        request.setAttribute("student", student);
        return Params.STUDENT_COURSE_LIST_JSP;
    }
}
