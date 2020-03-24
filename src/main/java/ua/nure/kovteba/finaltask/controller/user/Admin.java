package ua.nure.kovteba.finaltask.controller.user;

import ua.nure.kovteba.finaltask.dao.car.CarDAOImpl;
import ua.nure.kovteba.finaltask.dao.carbrand.CarBrandDAO;
import ua.nure.kovteba.finaltask.dao.carbrand.CarBrandDAOImpl;
import ua.nure.kovteba.finaltask.dao.flight.FlightDAOImpl;
import ua.nure.kovteba.finaltask.dao.request.RequestDAOImpl;
import ua.nure.kovteba.finaltask.dao.user.UserDAOImpl;
import ua.nure.kovteba.finaltask.enumlist.Role;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.UUID;

@WebServlet(
        name = "admin",
        urlPatterns = "/admin"
)
public class Admin extends HttpServlet {

    private static UserDAOImpl userDAO = new UserDAOImpl();
    private static FlightDAOImpl flightDAO = new FlightDAOImpl();
    private static RequestDAOImpl requestDAO = new RequestDAOImpl();
    private static CarBrandDAOImpl carBrandDAO = new CarBrandDAOImpl();
    private static CarDAOImpl carDAO = new CarDAOImpl();

    private static String token = null;



    @Override()
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        token = req.getParameter("token");
        if (token == null){
            token = UUID.randomUUID().toString();
        }

        System.out.println("ADMIN : " + token);


        //flights and requests section
        req.setAttribute("flightsList", flightDAO.getAllFlight());

        //drivers section
        req.setAttribute("driversList", userDAO.getUserByRole(Role.DRIVER));

        //set token
        req.setAttribute("token", token);


        RequestDispatcher dispatcher = req.getRequestDispatcher(
                "/WEB-INF/templates/adminPage.jsp");
        dispatcher.forward(req, resp);
    }

}
