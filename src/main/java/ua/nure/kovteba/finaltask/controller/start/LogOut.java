package ua.nure.kovteba.finaltask.controller.start;

import ua.nure.kovteba.finaltask.dao.token.TokenDAOImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "logOut",
        urlPatterns = "/logOut"
)
public class LogOut extends HttpServlet {

    private static TokenDAOImpl tokenDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        tokenDAO = new TokenDAOImpl();
        super.init(config);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //get token from page
        String token = req.getParameter("token");
        tokenDAO.deleteTokenByToken(token);
        token = null;


        req.setAttribute("token", null);
        resp.sendRedirect("index?token=" + token);
    }
}
