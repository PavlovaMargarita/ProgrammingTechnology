package dao.impl;

import dao.TeacherDao;
import model.Course;
import model.Mark;
import model.Student;
import param.Params;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class TeacherDaoImpl implements TeacherDao {
    private static TeacherDaoImpl ourInstance = new TeacherDaoImpl();

    public static TeacherDaoImpl getInstance() {
        return ourInstance;
    }

    private TeacherDaoImpl() {
    }

    @Override
    public List<Course> getCourses(int userId) throws SQLException {

        List<Course> courseList = new ArrayList<>();
        Connection connect = null;
        PreparedStatement statement = null;
        try {
            Class.forName(Params.bundle.getString("urlDriver"));
            connect = DriverManager.getConnection(Params.bundle.getString("urlDB"),
                    Params.bundle.getString("userDB"), Params.bundle.getString("passwordDB"));

            String getTeacherId = "select teacher.id from teacher " +
                    "where teacher.user_id = ?";

            statement = connect.prepareStatement(getTeacherId);
            statement.setInt(1, userId);

            ResultSet teacherIdSet = statement.executeQuery();
            teacherIdSet.next();
            int teacherId = teacherIdSet.getInt("teacher.id");

            String selectCourse = "select general_course_catalog.title, course_catalog.id from course_catalog " +
                    "inner join general_course_catalog on course_catalog.general_course_catalog_id=general_course_catalog.id " +
                    "where course_catalog.teacher_id = ?";

            statement = connect.prepareStatement(selectCourse);
            statement.setInt(1, teacherId);
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

    @Override
    public void saveMarks(List<Student> students,int courseId) throws SQLException {
        Connection connect = null;
        PreparedStatement statement = null;
        try {
            Class.forName(Params.bundle.getString("urlDriver"));
            connect = DriverManager.getConnection(Params.bundle.getString("urlDB"),
                    Params.bundle.getString("userDB"), Params.bundle.getString("passwordDB"));

            String selectCourse = "update mark set mark= ? where student_id= ? and course_catalog_id=?";
            statement = connect.prepareStatement(selectCourse);
            for(Student s:students){
                for(Mark m:s.getGeneralCourses()){
                    if(m.getCourse().getId()==courseId){
                        statement.setInt(1,m.getMark());
                        statement.setLong(2,s.getId());
                        statement.setInt(3,courseId);
                        break;

                    }
                }
            }
            statement.execute();




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
    public List<Student> getStudentsWithMarks(int courseId) throws SQLException {
        List<Student> studentList = new ArrayList<>();
        Connection connect = null;
        PreparedStatement statement = null;
        try {
            Class.forName(Params.bundle.getString("urlDriver"));
            connect = DriverManager.getConnection(Params.bundle.getString("urlDB"),
                    Params.bundle.getString("userDB"), Params.bundle.getString("passwordDB"));

            String selectCourse = "select p.student_id,u.first_name, u.last_name, u.patronymic,p.mark from user as u inner join (select m.student_id, m.mark, s.user_id from mark as m left join student as s on s.id=m.student_id where m.course_catalog_id = ?) as p on u.id=p.user_id";

            statement = connect.prepareStatement(selectCourse);
            statement.setInt(1, courseId);
            ResultSet resultStudent = statement.executeQuery();
            while (resultStudent.next()){
                Student student = new Student();
                student.setId(resultStudent.getInt("p.student_id"));
                student.setFirstName(resultStudent.getString("u.first_name"));
                student.setLastName(resultStudent.getString("u.last_name"));
                student.setPatronymic(resultStudent.getString("u.patronymic"));
                int m = resultStudent.getInt("p.mark");
                Course c = new Course();
                c.setId(courseId);
                Mark mark = new Mark(c,m);
                List<Mark> l  =new ArrayList<>();
                l.add(mark);
                student.setGeneralCourses(l);
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


    @Override
    public List<Course> getFreeCourse() throws SQLException {
        List<Course> courseList = new ArrayList<>();
        Connection connect = null;
        PreparedStatement statement = null;
        try {
            Class.forName(Params.bundle.getString("urlDriver"));
            connect = DriverManager.getConnection(Params.bundle.getString("urlDB"),
                    Params.bundle.getString("userDB"), Params.bundle.getString("passwordDB"));

            String selectAllCourseCatalogStr = "select * from course_catalog";

            statement = connect.prepareStatement(selectAllCourseCatalogStr);

            ResultSet result = statement.executeQuery();
            List<Integer> selectAllCourseCatalog = new ArrayList();
            while (result.next()){
                selectAllCourseCatalog.add(result.getInt("course_catalog.general_course_catalog_id"));
            }

            String selectAllGeneralCourseCatalogStr = "select * from general_course_catalog";

            statement = connect.prepareStatement(selectAllGeneralCourseCatalogStr);

            result = statement.executeQuery();
            List<Course> selectAllGeneralCourseCatalog = new ArrayList<>();
            while (result.next()){
                int course_id = result.getInt("general_course_catalog.id");
                if(!selectAllCourseCatalog.contains(course_id)) {
                    String title = result.getString("general_course_catalog.title");
                    Course course = new Course();
                    course.setId(course_id);
                    course.setTitle(title);
                    courseList.add(course);
                }
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
    public void deleteCourse(int userId, int courseId) throws SQLException {
        Connection connect = null;
        PreparedStatement statement = null;
        try {
            Class.forName(Params.bundle.getString("urlDriver"));
            connect = DriverManager.getConnection(Params.bundle.getString("urlDB"),
                    Params.bundle.getString("userDB"), Params.bundle.getString("passwordDB"));

            String getTeacherId = "select teacher.id from teacher " +
                    "where teacher.user_id = ?";

            statement = connect.prepareStatement(getTeacherId);
            statement.setInt(1, userId);

            ResultSet teacherIdSet = statement.executeQuery();
            teacherIdSet.next();
            int teacherId = teacherIdSet.getInt("teacher.id");

            String selectMarks = "delete from course_catalog where course_catalog.general_course_catalog_id = ?" +
                    " and course_catalog.teacher_id = ?";
            statement = connect.prepareStatement(selectMarks);
            statement.setInt(1, courseId);
            statement.setInt(2, teacherId);
            statement.execute();

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
    public void addCourse(int userId, int courseId) throws SQLException {
        Connection connect = null;
        PreparedStatement statement = null;
        try {
            Class.forName(Params.bundle.getString("urlDriver"));
            connect = DriverManager.getConnection(Params.bundle.getString("urlDB"),
                    Params.bundle.getString("userDB"), Params.bundle.getString("passwordDB"));

            String getTeacherId = "select teacher.id from teacher " +
                    "where teacher.user_id = ?";

            statement = connect.prepareStatement(getTeacherId);
            statement.setInt(1, userId);

            ResultSet teacherIdSet = statement.executeQuery();
            teacherIdSet.next();
            int teacherId = teacherIdSet.getInt("teacher.id");

            String selectMarks = "insert into course_catalog (general_course_catalog_id, teacher_id) " +
                    "values (?,?)";
            statement = connect.prepareStatement(selectMarks);
            statement.setInt(1, courseId);
            statement.setInt(2, teacherId);
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
