package ua.nure.kovteba.finaltask.controller.user;

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
import ua.nure.kovteba.finaltask.entity.EmploymentStatus;
import ua.nure.kovteba.finaltask.entity.Token;
import ua.nure.kovteba.finaltask.entity.User;
import ua.nure.kovteba.finaltask.enumlist.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@WebServlet(
        name = "driver",
        urlPatterns = "/driver"
)
public class Driver extends HttpServlet {

    //Create logger
    private static Logger log = Logger.getLogger(Driver.class.getName());

    private static UserDAO userDAO;
    private static FlightDAO flightDAO;
    private static RequestDAO requestDAO;
    private static CarBrandDAO carBrandDAO;
    private static CarDAO carDAO;
    private static EmploymentStatusDAO employmentStatusDAO;
    private static TokenDAO tokenDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userDAO = new UserDAOImpl();
        flightDAO = new FlightDAOImpl();
        requestDAO = new RequestDAOImpl();
        carBrandDAO = new CarBrandDAOImpl();
        carDAO = new CarDAOImpl();
        employmentStatusDAO = new EmploymentStatusDAOImpl();
        tokenDAO = new TokenDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //get user token from session
        String userToken = String.valueOf(req.getSession().getAttribute("userToken"));

        log.info("user token session--> " + userToken + ", class: " + this.getClass());

        Token token = tokenDAO.getTokenByToken(userToken);
        User user = userDAO.getUserById(token.getUser());
        if (token != null && user.getRole().getRoleValue().equals("DRIVER")) {
            //flights and requests section

            //set user info
            req.setAttribute("user", user);

            //set requests with status OPEN
            req.setAttribute("requestsListOpen",
                    requestDAO.getAllRequestByDriverAndRequestStatus(user, RequestStatus.OPEN));

            //set requests all
            req.setAttribute("requestsListAll", requestDAO.getAllRequestByDriver(user));

            //set requests with status CLOSED
            req.setAttribute("requestsListClosed",
                    requestDAO.getAllRequestByDriverAndRequestStatus(user, RequestStatus.CLOSED));

            req.setAttribute("flightListByUser",flightDAO.getAllFlightByDriver(user));


            //car section
            //set car list
            req.setAttribute("carClassList", CarClass.getListCarClass());


            RequestDispatcher dispatcher = req.getRequestDispatcher(
                    "/WEB-INF/templates/driver.jsp");
            dispatcher.forward(req, resp);
        } else {
            resp.sendRedirect("");
        }

    }

}
