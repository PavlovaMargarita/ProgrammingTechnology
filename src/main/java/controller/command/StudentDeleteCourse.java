package controller.command;

import dao.impl.StudentDaoImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class StudentDeleteCourse implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        StudentDaoImpl.getInstance().deleteCourse();
        return null;
    }
}
