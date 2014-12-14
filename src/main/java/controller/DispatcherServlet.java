package controller;

import controller.command.Authorization;

import controller.command.*;
import param.Params;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DispatcherServlet extends HttpServlet {
    private Map<String, Command> commandMap;

    @Override
    public void init(ServletConfig config) {
        commandMap = new HashMap<>();
        commandMap.put(Params.STUDENT_GET_MARKS, new StudentGetMarks());
        commandMap.put(Params.TEACHER_GET_COURSE, new TeacherGetCourses());
        commandMap.put(Params.TEACHER_GET_STUDENT_BY_COURSE_ID, new TeacherGetStudentByCourseId());
        commandMap.put(Params.STUDENT_GET_COURSES, new StudentGetCourses());
        commandMap.put(Params.STUDENT_EDIT_COURSES, new StudentEditCourses());
        commandMap.put(Params.AUTHORIZATION ,new Authorization());
        commandMap.put(Params.STUDENT_SAVE_COURSE, new StudentSaveCourse());
        commandMap.put(Params.TEACHER_SPECIFY_COURSE, new TeacherSpecifyCourse());
        commandMap.put(Params.TEACHER_EDIT_COURSES, new TeacherEditCourses());
        commandMap.put(Params.TEACHER_SAVE_COURSE, new TeacherSaveCourse());
    }

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        String method = request.getParameter(Params.METHOD);
        Command command = commandMap.get(method);

        String page = null;
        try {
            page = command.execute(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher(page).forward(request, response);

    }
}
