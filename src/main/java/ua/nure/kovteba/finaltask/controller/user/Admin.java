package ua.nure.kovteba.finaltask.controller.user;

import ua.nure.kovteba.finaltask.dao.car.CarDAOImpl;
import ua.nure.kovteba.finaltask.dao.carbrand.CarBrandDAO;
import ua.nure.kovteba.finaltask.dao.carbrand.CarBrandDAOImpl;
import ua.nure.kovteba.finaltask.dao.flight.FlightDAOImpl;
import ua.nure.kovteba.finaltask.dao.request.RequestDAOImpl;
import ua.nure.kovteba.finaltask.dao.user.UserDAOImpl;
import ua.nure.kovteba.finaltask.enumlist.RequestStatus;
import ua.nure.kovteba.finaltask.enumlist.Role;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
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

    private static UserDAOImpl userDAO;
    private static FlightDAOImpl flightDAO;
    private static RequestDAOImpl requestDAO;
    private static CarBrandDAOImpl carBrandDAO;
    private static CarDAOImpl carDAO;

    private static String token = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userDAO = new UserDAOImpl();
        flightDAO = new FlightDAOImpl();
        requestDAO = new RequestDAOImpl();
        carBrandDAO = new CarBrandDAOImpl();
        carDAO = new CarDAOImpl();
    }

    @Override()
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        token = req.getParameter("token");
        if (token == null){
            token = UUID.randomUUID().toString();
        }

        System.out.println("ADMIN : " + token);


        //flights and requests section
        //set flight list
        req.setAttribute("flightsList", flightDAO.getAllFlight());
        //set requests with status OPEN
        req.setAttribute("requestsList", requestDAO.getAllRequestByStatus(RequestStatus.OPEN));
        //set car with status FREE
        req.setAttribute("carsListForRequest", carDAO.getListCarFreeAndGood());

        //dispatcher section
        //set dispatcher list
        req.setAttribute("dispatcherList", userDAO.getUserByRole(Role.DISPATCHER));

        //drivers section
        //set driver list
        req.setAttribute("driversList", userDAO.getUserByRole(Role.DRIVER));

        //car section
        //set car list
        req.setAttribute("carsList", carDAO.getAllCars());






        //set token
        req.setAttribute("token", token);


        RequestDispatcher dispatcher = req.getRequestDispatcher(
                "/WEB-INF/templates/adminPage.jsp");
        dispatcher.forward(req, resp);
    }

}
