package controller.command;

import model.Course;
import model.Student;
import param.Params;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Margarita on 09.12.2014.
 */
public class StudentEditCourses implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Student student = connectToDB();
        String param = request.getParameter("param");
        request.setAttribute("student", student);
        request.setAttribute("param", param);
        return Params.STUDENT_EDIT_COURSE_JSP;
    }

    private Student connectToDB() throws SQLException {
        int id = 1;
        Map<Course, Integer> mainCourses = new HashMap<>();
        List<Course> addCourses = new ArrayList<>();
        Connection connect = null;
        PreparedStatement statement = null;
        try {
            Class.forName(Params.bundle.getString("urlDriver"));
            connect = DriverManager.getConnection(Params.bundle.getString("urlDB"),
                    Params.bundle.getString("userDB"), Params.bundle.getString("passwordDB"));

            String selectMarks = "select student.main_course_1_id,student.main_course_2_id, student.main_course_3_id, student.main_course_4_id," +
                    "student.add_course_1_id, student.add_course_2_id from student where mark.student_id = ?";
            statement = connect.prepareStatement(selectMarks);
            statement.setInt(1, id);
            ResultSet resultMark = statement.executeQuery();
            while (resultMark.next()){
                int course_id = resultMark.getInt("student.main_course_1_id");
                mainCourses.put(getCourseById(statement, connect, course_id), null);
                course_id = resultMark.getInt("student.main_course_2_id");
                mainCourses.put(getCourseById(statement, connect, course_id), null);
                course_id = resultMark.getInt("student.main_course_3_id");
                mainCourses.put(getCourseById(statement, connect, course_id), null);
                course_id = resultMark.getInt("student.main_course_4_id");
                mainCourses.put(getCourseById(statement, connect, course_id), null);

                course_id = resultMark.getInt("student.add_course_1_id");
                addCourses.add(getCourseById(statement, connect, course_id));
                course_id = resultMark.getInt("student.add_course_2_id");
                addCourses.add(getCourseById(statement, connect, course_id));
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
            Student student= new Student();
            student.setAdditionalCourseList(addCourses);
            student.setGeneralCourses(mainCourses);
            return student;
        }

    }

    private Course getCourseById(PreparedStatement statement, Connection connect, int course_id) throws SQLException {
        String selectCourseDescription = "select general_course_catalog.title from course_catalog " +
                "inner join general_course_catalog on course_catalog.general_course_catalog_id=general_course_catalog.id " +
                "where course_catalog.id = ?";
        statement = connect.prepareStatement(selectCourseDescription);
        statement.setInt(1, course_id);
        ResultSet titleSet = statement.executeQuery();
        titleSet.next();
        String title = titleSet.getString("general_course_catalog.title");
        Course course = new Course();
        course.setId(course_id);
        course.setTitle(title);
        return course;
    }
}
