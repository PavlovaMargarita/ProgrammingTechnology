package controller.command;

import model.Course;
import param.Params;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Margarita on 08.12.2014.
 */
public class TeacherGetCourses implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    private List<Course> connectToDb() throws SQLException {
        int id = 0;
        List<Course> courseList = new ArrayList<>();
        Connection connect = null;
        PreparedStatement statement = null;
        try {
            Class.forName(Params.bundle.getString("urlDriver"));
            connect = DriverManager.getConnection(Params.bundle.getString("urlDB"),
                    Params.bundle.getString("userDB"), Params.bundle.getString("passwordDB"));

            String selectCourse = "select general_course_catalog.title, course_catalog.id from course_catalog " +
                    "inner join general_course_catalog on course_catalog.general_course_catalog_id=general_course_catalog.id " +
                    "where course_catalog.teacher_id = ?";

            statement = connect.prepareStatement(selectCourse);
            statement.setInt(1, id);
            ResultSet resultMark = statement.executeQuery();
            while (resultMark.next()){
                int course_id = resultMark.getInt("course_catalog.id");
                String title = resultMark.getString("general_course_catalog.title");
                Course course = new Course();
                course.setId(course_id);
                course.setTitle(title);
                courseList.add(course);
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
            return courseList;
        }

    }
}
