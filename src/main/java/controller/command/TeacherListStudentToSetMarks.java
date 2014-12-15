package controller.command;

import dao.impl.TeacherDaoImpl;
import model.Student;
import param.Params;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: User
 * Date: 14.12.14
 * Time: 23:09
 * To change this template use File | Settings | File Templates.
 */
public class TeacherListStudentToSetMarks implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int course_id = Integer.parseInt(request.getParameter("course_id"));
        List<Student> studentList = TeacherDaoImpl.getInstance().getStudentsWithMarks(course_id);
        request.setAttribute("studentList", studentList);
        request.getSession().setAttribute("studentList", studentList);
        request.setAttribute("courseId",course_id);
        return Params.TEACHER_LIST_STUDENTS_TO_SET_MARKS_JSP;
    }
}
