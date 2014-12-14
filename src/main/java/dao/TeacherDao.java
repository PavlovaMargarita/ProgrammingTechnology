package dao;

import model.Course;
import model.Student;

import java.sql.SQLException;
import java.util.List;

public interface TeacherDao {
    public List<Course> getCourses(int userId) throws SQLException;
    public List<Student> getStudent(int courseId) throws SQLException;
    public List<Course> getFreeCourse() throws SQLException;
    public void deleteCourse(int userID, int courseId) throws SQLException;
    public void addCourse(int userID, int courseId) throws SQLException;
}
