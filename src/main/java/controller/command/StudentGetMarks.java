package controller.command;

import model.Course;
import param.Params;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class StudentGetMarks implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Map<Course, Integer> marks = connectToDb();
        request.setAttribute("marks", marks);
        return Params.STUDENT_MARKS_JSP;
    }

    private Map<Course,Integer> connectToDb() throws SQLException {
        int id = 1;
        Map<Course, Integer> courseMark = new HashMap<>();
        Connection connect = null;
        PreparedStatement statement = null;
        try {
            Class.forName(Params.bundle.getString("urlDriver"));
             connect = DriverManager.getConnection(Params.bundle.getString("urlDB"),
                    Params.bundle.getString("userDB"),Params.bundle.getString("passwordDB"));

            String selectMarks = "select mark.id, mark.mark, mark.course_catalog_id from mark where mark.student_id = ?";
            statement = connect.prepareStatement(selectMarks);
            statement.setInt(1, id);
            ResultSet resultMark = statement.executeQuery();
            while (resultMark.next()){
                int mark = resultMark.getInt("mark");
                int course_id = resultMark.getInt("course_catalog_id");
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
                courseMark.put(course, mark);
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
            return courseMark;
        }

    }
}
