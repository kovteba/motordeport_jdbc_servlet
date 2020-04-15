package ua.nure.kovteba.finaltask.i18n;

import ua.nure.kovteba.finaltask.controller.user.Admin;
import ua.nure.kovteba.finaltask.dao.car.CarDAO;
import ua.nure.kovteba.finaltask.dao.car.CarDAOImpl;
import ua.nure.kovteba.finaltask.dao.carbrand.CarBrandDAO;
import ua.nure.kovteba.finaltask.dao.carbrand.CarBrandDAOImpl;
import ua.nure.kovteba.finaltask.dao.employmentstatus.EmploymentStatusDAO;
import ua.nure.kovteba.finaltask.dao.employmentstatus.EmploymentStatusDAOImpl;
import ua.nure.kovteba.finaltask.dao.flight.FlightDAO;
import ua.nure.kovteba.finaltask.dao.flight.FlightDAOImpl;
import ua.nure.kovteba.finaltask.dao.request.RequestDAO;
import ua.nure.kovteba.finaltask.dao.request.RequestDAOImpl;
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
        name = "i18nRU",
        urlPatterns = "/ru"
)
public class i18nRU extends HttpServlet {

    //Create logger
    private static Logger log = Logger.getLogger(i18nRU.class.getName());

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

        req.getSession().setAttribute("i18n", "MessagesBundle_ru_RU");

        String userToken = "0";

        if (req.getSession().getAttribute("userToken") != null){
            userToken = String.valueOf(req.getSession().getAttribute("userToken"));
        }

        User user = null;
        if (!userToken.equals("0")){
            user = userDAO.getUserById(tokenDAO.getTokenByToken(userToken).getUser());
        }

        if (userToken.equals("0")){
            resp.sendRedirect("");
        } else if (user.getRole().getRoleValue().equals("ADMIN")){
            resp.sendRedirect("admin");
        }

    }
}
