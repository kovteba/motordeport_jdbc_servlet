package ua.nure.kovteba.finaltask.controller;

import ua.nure.kovteba.finaltask.dao.flight.FlightDAOImpl;
import ua.nure.kovteba.finaltask.dao.token.TokenDAOImpl;
import ua.nure.kovteba.finaltask.dao.user.UserDAOImpl;
import ua.nure.kovteba.finaltask.entity.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "indexServlet",
        urlPatterns = "/"
)
public class IndexServlet extends HttpServlet {

    private static UserDAOImpl userDAO;
    private static TokenDAOImpl tokenDAO;
    private static FlightDAOImpl flightDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userDAO = new UserDAOImpl();
        tokenDAO = new TokenDAOImpl();
        flightDAO = new FlightDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //flights and requests section
        req.setAttribute("flightsList", flightDAO.getAllFlight());

        System.out.println("FROM INDEX");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("FROM INDEX POST");

        String phoneNumber = req.getParameter("phoneNumber");
        String password = req.getParameter("password");

        String token = null;
        User user = userDAO.getUserByUserPhoneNumber(phoneNumber);
        if (user.getPassword().equals(password)) {

            token = tokenDAO.createToken(user.getId());
            if (user.getRole().getRoleValue().equals("ADMIN")) {
                resp.sendRedirect("admin?token=" + token);
            }
        }


    }
}
