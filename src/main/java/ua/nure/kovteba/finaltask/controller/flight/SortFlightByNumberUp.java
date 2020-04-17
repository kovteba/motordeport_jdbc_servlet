package ua.nure.kovteba.finaltask.controller.flight;

import ua.nure.kovteba.finaltask.dao.token.TokenDAO;
import ua.nure.kovteba.finaltask.dao.token.TokenDAOImpl;
import ua.nure.kovteba.finaltask.dao.user.UserDAO;
import ua.nure.kovteba.finaltask.dao.user.UserDAOImpl;
import ua.nure.kovteba.finaltask.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet(
        name = "sortFlightByNumberUp",
        urlPatterns = "/sortFlightByNumberUp"
)
public class SortFlightByNumberUp extends HttpServlet {

    //Create logger
    private static Logger log = Logger.getLogger(SortFlightByNumberUp.class.getName());

    private static UserDAO userDAO;
    private static TokenDAO tokenDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        userDAO = new UserDAOImpl();
        tokenDAO = new TokenDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userToken = "0";

        if (req.getSession().getAttribute("userToken") != null) {
            userToken = String.valueOf(req.getSession().getAttribute("userToken"));
        }

        User user = null;
        if (!userToken.equals("0")) {
            user = userDAO.getUserById(tokenDAO.getTokenByToken(userToken).getUser());

            if (req.getParameter("flightNumberValue") != null) {
                req.getSession().setAttribute("typeSort", req.getParameter("flightNumberValue"));
            }
        }

        if (userToken.equals("0")) {
            resp.sendRedirect("");
        } else if (user.getRole().getRoleValue().equals("ADMIN")) {
            resp.sendRedirect("admin");
        } else if (user.getRole().getRoleValue().equals("DRIVER")) {
            resp.sendRedirect("driver");
        } else if (user.getRole().getRoleValue().equals("DISPATCHER")) {
            resp.sendRedirect("dispatcher");
        }

    }

}
