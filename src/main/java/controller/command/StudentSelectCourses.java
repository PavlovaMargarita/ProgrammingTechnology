package controller.command;

import dao.impl.StudentDaoImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
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
        StudentDaoImpl.getInstance().selectCourses(mainCourseList, additionalCourseList);
        return null;
    }

}
