package ua.nure.kovteba.finaltask.controller.user;

import ua.nure.kovteba.finaltask.dao.flight.FlightDAOImpl;
import ua.nure.kovteba.finaltask.dao.user.UserDAOImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "admin",
        urlPatterns = "/admin"
)
public class Admin extends HttpServlet {

    private static UserDAOImpl userDAO = new UserDAOImpl();
    private static FlightDAOImpl flightDAO = new FlightDAOImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        req.setAttribute("studentRecord", userDAO.getUserById(1L));
        req.setAttribute("flightList", flightDAO.getAllFlight());

        RequestDispatcher dispatcher = req.getRequestDispatcher(
                "/WEB-INF/templates/adminPage.jsp");
        dispatcher.forward(req, resp);
    }
}
