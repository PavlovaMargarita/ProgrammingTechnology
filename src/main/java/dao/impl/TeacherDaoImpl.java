package dao.impl;

import dao.TeacherDao;
import model.Course;
import model.Student;
import param.Params;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Margarita on 09.12.2014.
 */
public class TeacherDaoImpl implements TeacherDao {
    private static TeacherDaoImpl ourInstance = new TeacherDaoImpl();

    public static TeacherDaoImpl getInstance() {
        return ourInstance;
    }

    private TeacherDaoImpl() {
    }

    @Override
    public List<Course> getCourses() throws SQLException {
        int id = 1;
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

    @Override
    public List<Student> getStudent(int courseId) throws SQLException {
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
