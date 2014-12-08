package controller.command;

import dao.impl.StudentDaoImpl;
import model.Course;
import param.Params;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Map;

public class StudentGetMarks implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Map<Course, Integer> marks = StudentDaoImpl.getInstance().getMarks();
        request.setAttribute("marks", marks);
        return Params.STUDENT_MARKS_JSP;
    }
}
