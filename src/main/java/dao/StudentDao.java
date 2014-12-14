package dao;

import model.Course;
import model.Student;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface StudentDao {

    public Map<Course, Integer> getMarks(int userId) throws SQLException;
    public void deleteCourse(int userId) throws SQLException;
    public Student getCourses(int userId) throws SQLException;
    public void selectCourses(List<Integer> mainCourseList, List<Integer> additionalCourseList, int userId) throws SQLException;
    public void updateCourses(List<Integer> mainCourseList, List<Integer> additionalCourseList, int userId) throws SQLException;
    public List<Course> getAllCourses() throws SQLException;
}
