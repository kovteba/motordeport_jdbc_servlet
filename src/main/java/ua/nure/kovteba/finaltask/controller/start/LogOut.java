package ua.nure.kovteba.finaltask.controller.start;

import ua.nure.kovteba.finaltask.controller.user.CreateDispatcher;
import ua.nure.kovteba.finaltask.dao.token.TokenDAOImpl;
import ua.nure.kovteba.finaltask.dao.user.UserDAOImpl;
import ua.nure.kovteba.finaltask.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet(
        name = "logOut",
        urlPatterns = "/logOut"
)
public class LogOut extends HttpServlet {

    //Create logger
    private static Logger log = Logger.getLogger(LogOut.class.getName());

    private static TokenDAOImpl tokenDAO;
    private static UserDAOImpl userDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        tokenDAO = new TokenDAOImpl();
        userDAO = new UserDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userToken = "0";

        if (req.getSession().getAttribute("userToken") != null){
            userToken = String.valueOf(req.getSession().getAttribute("userToken"));
        }

        log.info("user token session--> " + userToken + ", class: " + this.getClass());

        if (!userToken.equals("0")){
            tokenDAO.deleteTokenByToken(userToken);
            req.getSession().setAttribute("userToken", "");
        }

        resp.sendRedirect("");

    }
}
