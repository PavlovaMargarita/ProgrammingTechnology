package controller;

import controller.command.Authorization;
import controller.command.Command;
import controller.command.StudentGetMarks;
import param.Params;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DispatcherServlet extends HttpServlet {
    private Map<String, Command> commandMap;

    @Override
    public void init(ServletConfig config) {
        commandMap = new HashMap<String, Command>();
        commandMap.put(Params.GET_STUDENT_MARKS, new StudentGetMarks());
        commandMap.put(Params.AUTHORIZATION,new Authorization());
    }

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        String method = request.getParameter(Params.METHOD);
        Command command = commandMap.get(method);

        String page = command.execute(request, response);
        request.getRequestDispatcher(page).forward(request, response);

    }
}
