package controller.command;

import model.Student;
import param.Params;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Margarita on 08.12.2014.
 */
public class TeacherGetStudentByCourseId implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int course_id = Integer.parseInt(request.getParameter("course_id"));
        List<Student> studentList = connectToDb(course_id);
        request.setAttribute("studentList", studentList);
        return Params.TEACHER_STUDENT_LIST_JSP;
    }

    private List<Student> connectToDb(int courseId) throws SQLException {
        List<Student> studentList = new ArrayList<>();
        Connection connect = null;
        PreparedStatement statement = null;
        try {
            Class.forName(Params.bundle.getString("urlDriver"));
            connect = DriverManager.getConnection(Params.bundle.getString("urlDB"),
                    Params.bundle.getString("userDB"), Params.bundle.getString("passwordDB"));

            String selectCourse = "select user.id, user.first_name, user.last_name, user.patronymic from student inner join user on student.user_id=user.id where " +
                    "student.main_course_1_id = ? or student.main_course_2_id = ? or student.main_course_3_id = ? or student.main_course_4_id = ?";

            statement = connect.prepareStatement(selectCourse);
            statement.setInt(1, courseId);
            statement.setInt(2, courseId);
            statement.setInt(3, courseId);
            statement.setInt(4, courseId);
            ResultSet resultStudent = statement.executeQuery();
            while (resultStudent.next()){
                Student student = new Student();
                student.setId(resultStudent.getInt("user.id"));
                student.setFirstName(resultStudent.getString("user.first_name"));
                student.setLastName(resultStudent.getString("user.last_name"));
                student.setPatronymic(resultStudent.getString("user.patronymic"));
                studentList.add(student);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(connect != null)
                connect.close();
            if(statement != null)
                statement.close();
            return studentList;
        }

    }
}
