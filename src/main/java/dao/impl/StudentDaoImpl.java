package dao.impl;

import dao.StudentDao;
import model.Course;
import model.Student;
import param.Params;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Margarita on 09.12.2014.
 */
public class StudentDaoImpl implements StudentDao{
    private static StudentDaoImpl ourInstance = new StudentDaoImpl();

    public static StudentDaoImpl getInstance() {
        return ourInstance;
    }

    private StudentDaoImpl() {
    }


    @Override
    public Map<Course, Integer> getMarks() throws SQLException {
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

    @Override
    public void deleteCourse() throws SQLException {
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

    @Override
    public Student getCourses() throws SQLException {
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
                    "student.add_course_1_id, student.add_course_2_id from student where student.id = ?";
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
            Student student = new Student();
            student.setAdditionalCourseList(addCourses);
            student.setGeneralCourses(mainCourses);
            return student;
        }

    }

    @Override
    public void selectCourses(List<Integer> mainCourseList, List<Integer> additionalCourseList) throws SQLException {
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

    @Override
    public void updateCourses(List<Integer> mainCourseList, List<Integer> additionalCourseList) throws SQLException {
        int id = 1;
        Connection connect = null;
        PreparedStatement statement = null;
        try {
            Class.forName(Params.bundle.getString("urlDriver"));
            connect = DriverManager.getConnection(Params.bundle.getString("urlDB"),
                    Params.bundle.getString("userDB"), Params.bundle.getString("passwordDB"));

            String selectMarks = "update student set (main_course_1_id, main_course_2_id, main_course_3_id, main_course_4_id," +
                    "add_course_1_id,add_course_2_id) values (?,?,?,?,?,?) where student.id = ?";
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

    @Override
    public List<Course> getAllCourses() throws SQLException {
        List<Course> courseList = new ArrayList<>();
        Connection connect = null;
        PreparedStatement statement = null;
        try{
            Class.forName(Params.bundle.getString("urlDriver"));
            connect = DriverManager.getConnection(Params.bundle.getString("urlDB"),
                    Params.bundle.getString("userDB"), Params.bundle.getString("passwordDB"));

            String selectMarks = "select course_catalog.id, general_course_catalog.title from course_catalog " +
                    "inner join general_course_catalog on course_catalog.general_course_catalog_id = general_course_catalog.id";
            statement = connect.prepareStatement(selectMarks);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Course course = new Course();
                course.setId(resultSet.getInt("course_catalog.id"));
                course.setTitle(resultSet.getString("general_course_catalog.title"));
                courseList.add(course);
            }

        } finally {
            if(connect != null)
                connect.close();
            if(statement != null)
                statement.close();
            return courseList;
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
