package controller.command;

import dao.impl.UserDaoImpl;
import param.Params;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Authorization implements Command {
    private UserDaoImpl userDao;

    public Authorization(){
        userDao=new UserDaoImpl();
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter(Params.LOGIN_NAME);
        String password = request.getParameter(Params.LOGIN_PASSWORD);
        userDao.authorization(login,password);
        HttpSession httpSession = request.getSession(true);
        httpSession.setAttribute("role", userDao.getRole());
        httpSession.setAttribute("idUser",userDao.getId());
        return "index.jsp";
    }
}
