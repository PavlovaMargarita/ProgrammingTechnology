package controller.command;

import param.Params;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDeleteCourse implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        connectToDb();
        return null;
    }

    private void connectToDb() throws SQLException {
        int id = 1;
        Connection connect = null;
        PreparedStatement statement = null;
        try {
            Class.forName(Params.bundle.getString("urlDriver"));
            connect = DriverManager.getConnection(Params.bundle.getString("urlDB"),
                    Params.bundle.getString("userDB"), Params.bundle.getString("passwordDB"));

            String selectMarks = "update student set main_course_1_id = NULL, main_course_2_id = NULL, main_course_3_id = NULL, main_course_4_id = NULL," +
                    "add_course_1_id = NULL,add_course_2_id = NULL where student.id = ?";
            statement = connect.prepareStatement(selectMarks);
            statement.setInt(1, id);
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
