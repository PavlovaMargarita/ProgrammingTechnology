package dao;

import model.Course;
import model.Student;

import java.sql.SQLException;
import java.util.List;

public interface TeacherDao {
    public List<Course> getCourses() throws SQLException;
    public List<Student> getStudent(int courseId) throws SQLException;
    public List<Student> getStudentsWithMarks(int courseId) throws SQLException;
    public void saveMarks(List<Student> students,int courseId) throws SQLException;
}
