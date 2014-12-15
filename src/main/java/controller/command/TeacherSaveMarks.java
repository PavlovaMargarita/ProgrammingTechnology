package controller.command;

import dao.impl.TeacherDaoImpl;
import model.Mark;
import model.Student;
import param.Params;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: User
 * Date: 15.12.14
 * Time: 22:05
 * To change this template use File | Settings | File Templates.
 */
public class TeacherSaveMarks implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        List<Student> st = (List)request.getSession().getAttribute("studentList");
        int courseId=Integer.parseInt(request.getParameter("courseId"));
        for(Student s:st){
            String mark =(String)request.getParameter(Long.toString(s.getId()));
            for(Mark m:s.getGeneralCourses()){
                if(m.getCourse().getId()==courseId){
                    m.setMark(Integer.valueOf(mark));
                    break;
                }
            }
        }

        TeacherDaoImpl.getInstance().saveMarks(st,courseId);
        return Params.INDEX_JSP;
    }
}
