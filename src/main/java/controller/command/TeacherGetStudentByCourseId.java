package controller.command;

import dao.impl.TeacherDaoImpl;
import model.Student;
import param.Params;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Margarita on 08.12.2014.
 */
public class TeacherGetStudentByCourseId implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int course_id = Integer.parseInt(request.getParameter("course_id"));
        List<Student> studentList = TeacherDaoImpl.getInstance().getStudent(course_id);
        request.setAttribute("studentList", studentList);
        return Params.TEACHER_STUDENT_LIST_JSP;
    }

}
