package ua.nure.kovteba.finaltask.controller.user;

import ua.nure.kovteba.finaltask.dao.car.CarDAOImpl;
import ua.nure.kovteba.finaltask.dao.carbrand.CarBrandDAO;
import ua.nure.kovteba.finaltask.dao.carbrand.CarBrandDAOImpl;
import ua.nure.kovteba.finaltask.dao.flight.FlightDAOImpl;
import ua.nure.kovteba.finaltask.dao.request.RequestDAOImpl;
import ua.nure.kovteba.finaltask.dao.user.UserDAOImpl;
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

    private static UserDAOImpl userDAO;
    private static FlightDAOImpl flightDAO;
    private static RequestDAOImpl requestDAO;
    private static CarBrandDAOImpl carBrandDAO;
    private static CarDAOImpl carDAO;

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

        //get token from HTML
        String token = req.getParameter("token");
        LOG.info("Admin page with token value --> " + token);
        //choose tab for open
        String value = req.getParameter("value");
        if (value == null) {
            req = chooseTab(req,
                    "show active",
                    "",
                    "",
                    "",
                    "active",
                    "",
                    "",
                    "");
        } else {
            switch (value) {
                case "DRIVER":
                    req = chooseTab(req,
                            "",
                            "",
                            "show active",
                            "",
                            "",
                            "",
                            "active",
                            "");
                    break;
                case "DISPATCHER":
                    req = chooseTab(req,
                            "",
                            "show active",
                            "",
                            "",
                            "",
                            "active",
                            "",
                            "");
                    break;
                case "CAR":
                    req = chooseTab(req,
                            "",
                            "",
                            "",
                            "show active",
                            "",
                            "",
                            "",
                            "active");
                    break;
            }
        }

        //flights and requests section
        //set flight list
        req.setAttribute("flightsList", flightDAO.getAllFlight());
        //set requests with status OPEN
        req.setAttribute("requestsListOpen", requestDAO.getAllRequestByStatus(RequestStatus.OPEN));
        req.setAttribute("requestsListAll", requestDAO.getAllRequest());
        req.setAttribute("requestsListClosed", requestDAO.getAllRequestByStatus(RequestStatus.CLOSED));

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
        req.setAttribute("carBrandList", carBrandDAO.getAllCarBrand());
        req.setAttribute("carClassList", CarClass.getListCarClass());
        req.setAttribute("carTechnicalStatusList", CarTechnicalStatus.getListCarTechnicalStatus());
        req.setAttribute("carStatus", CarStatus.getListCarStatus());


        //set token
        req.setAttribute("token", token);
        RequestDispatcher dispatcher = req.getRequestDispatcher(
                "/WEB-INF/templates/adminPage.jsp");
        dispatcher.forward(req, resp);
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
