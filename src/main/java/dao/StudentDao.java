package dao;

import model.Course;
import model.Student;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface StudentDao {

    public Map<Course, Integer> getMarks() throws SQLException;
    public void deleteCourse() throws SQLException;
    public Student getCourses() throws SQLException;
    public void selectCourses(List<Integer> mainCourseList, List<Integer> additionalCourseList) throws SQLException;
    public void updateCourses(List<Integer> mainCourseList, List<Integer> additionalCourseList) throws SQLException;
    public List<Course> getAllCourses() throws SQLException;
}
