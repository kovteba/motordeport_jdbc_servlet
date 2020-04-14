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
import ua.nure.kovteba.finaltask.entity.Token;
import ua.nure.kovteba.finaltask.enumlist.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.UUID;
import java.util.logging.Logger;

@WebServlet(
        name = "admin",
        urlPatterns = "/admin"
)
public class Admin extends HttpServlet {

    //Create logger
    private static Logger LOG = Logger.getLogger(Admin.class.getName());

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

    @Override()
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //get token from HTML
        String tokenFromHTML = req.getParameter("token");

        Token token = tokenDAO.getTokenByToken(tokenFromHTML);
        if (token != null && userDAO.getUserById(token.getUser()).getRole().getRoleValue().equals("ADMIN")) {
            LOG.info(this.getClass() + "\n Token value --> " + tokenFromHTML);
            //choose tab for open
            String value = req.getParameter("value");
            if (value == null) {
                req = chooseTab(req,
                        "show active", "", "", "",
                        "active", "", "", "");
            } else {
                switch (value) {
                    case "DRIVER":
                        req = chooseTab(req,
                                "", "", "show active", "",
                                "", "", "active", "");
                        break;
                    case "DISPATCHER":
                        req = chooseTab(req,
                                "", "show active", "", "",
                                "", "active", "", "");
                        break;
                    case "CAR":
                        req = chooseTab(req,
                                "", "", "", "show active",
                                "", "", "", "active");
                        break;
                }
            }

            //flights and requests section
            //set flight list
            req.setAttribute("flightsList", flightDAO.getAllFlight());
            //set requests with status OPEN
            req.setAttribute("requestsListOpen", requestDAO.getAllRequestByStatus(RequestStatus.OPEN));
            //set requests all
            req.setAttribute("requestsListAll", requestDAO.getAllRequest());
            //set requests with status CLOSED
            req.setAttribute("requestsListClosed", requestDAO.getAllRequestByStatus(RequestStatus.CLOSED));

            //set car with status FREE
            req.setAttribute("carsListForRequest", carDAO.getListCarFreeAndGood());

            //dispatcher section
            //set dispatcher list
            req.setAttribute("dispatcherList", userDAO.getUserByRole(Role.DISPATCHER));

            //drivers section
            //set driver list
            req.setAttribute("driversList", userDAO.getUserByRole(Role.DRIVER));
            req.setAttribute("freeDrivers", userDAO.getAllUsersByListId(employmentStatusDAO.getAllFreeDrivers()));

            //car section
            //set car list
            req.setAttribute("carsList", carDAO.getAllCars());
            req.setAttribute("carBrandList", carBrandDAO.getAllCarBrand());
            req.setAttribute("carClassList", CarClass.getListCarClass());
            req.setAttribute("carTechnicalStatusList", CarTechnicalStatus.getListCarTechnicalStatus());
            req.setAttribute("carStatus", CarStatus.getListCarStatus());

            //set token
            req.setAttribute("token", tokenFromHTML);
            RequestDispatcher dispatcher = req.getRequestDispatcher(
                    "/WEB-INF/templates/adminPage.jsp");
            dispatcher.forward(req, resp);
        } else {
            resp.sendRedirect("");
        }


    }

    private HttpServletRequest chooseTab(HttpServletRequest request,
                                         String flightShow, String dispatcherShow, String driversShow, String carsShow,
                                         String flightBtn, String dispatcherBtn, String driverBtn, String carBtn) {
        request.setAttribute("flightShow", flightShow);
        request.setAttribute("dispatcherShow", dispatcherShow);
        request.setAttribute("driversShow", driversShow);
        request.setAttribute("carsShow", carsShow);
        request.setAttribute("flightBtn", flightBtn);
        request.setAttribute("dispatcherBtn", dispatcherBtn);
        request.setAttribute("driverBtn", driverBtn);
        request.setAttribute("carBtn", carBtn);
        return request;
    }
}
