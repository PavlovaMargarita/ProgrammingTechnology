package controller.command;

import param.Params;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentSelectCourses implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        List<Integer> mainCourseList = new ArrayList<>();
        mainCourseList.add(Integer.parseInt(request.getParameter("main_course_1_id")));
        mainCourseList.add(Integer.parseInt(request.getParameter("main_course_2_id")));
        mainCourseList.add(Integer.parseInt(request.getParameter("main_course_3_id")));
        mainCourseList.add(Integer.parseInt(request.getParameter("main_course_4_id")));

        List<Integer> additionalCourseList = new ArrayList<>();
        additionalCourseList.add(Integer.parseInt(request.getParameter("add_course_1_id")));
        additionalCourseList.add(Integer.parseInt(request.getParameter("add_course_2_id")));
        connectToDb(mainCourseList, additionalCourseList);
        return null;
    }

    private void connectToDb(List<Integer> mainCourseList, List<Integer> additionalCourseList) throws SQLException {
        int id = 1;
        Connection connect = null;
        PreparedStatement statement = null;
        try {
            Class.forName(Params.bundle.getString("urlDriver"));
            connect = DriverManager.getConnection(Params.bundle.getString("urlDB"),
                    Params.bundle.getString("userDB"), Params.bundle.getString("passwordDB"));

            String selectMarks = "insert into student (main_course_1_id, main_course_2_id, main_course_3_id, main_course_4_id," +
                    "add_course_1_id,add_course_2_id) values (?,?,?,?,?,?) where student.id=?";
            statement = connect.prepareStatement(selectMarks);
            int i = 0;
            for(; i < mainCourseList.size(); i++)
                statement.setInt(i + 1, mainCourseList.get(i));
            for(; i < additionalCourseList.size(); i++)
                statement.setInt(i + 1, additionalCourseList.get(i - mainCourseList.size()));
            statement.setInt(i, id);
            statement.executeUpdate();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(connect != null)
                connect.close();
            if(statement != null)
                statement.close();
        }

    }
}
