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
import ua.nure.kovteba.finaltask.entity.Flight;
import ua.nure.kovteba.finaltask.entity.Token;
import ua.nure.kovteba.finaltask.entity.User;
import ua.nure.kovteba.finaltask.enumlist.*;
import ua.nure.kovteba.finaltask.util.ChooseSort;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@WebServlet(
        name = "dispatcher",
        urlPatterns = "/dispatcher"
)
public class Dispatcher extends HttpServlet {

    //Create logger
    private static Logger log = Logger.getLogger(Dispatcher.class.getName());

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

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //get user token from session
        String userToken = String.valueOf(req.getSession().getAttribute("userToken"));

        log.info("user token session--> " + userToken + ", class: " + this.getClass());

        Token token = tokenDAO.getTokenByToken(userToken);
        System.out.println("@@@@@@@@@@ : " + token);

        User user = userDAO.getUserById(token.getUser());

        System.out.println("USER : " + user);
        if (token != null && user.getRole().getRoleValue().equals("DISPATCHER")) {
            //flights and requests section

            //set user info
            req.setAttribute("user", user);

            //set flight list
            if (req.getSession().getAttribute("typeSort") != null) {
                String typeSort = String.valueOf(req.getSession().getAttribute("typeSort"));
                List<Flight> flightList = flightDAO.getAllFlight();
                req.setAttribute("flightsList", ChooseSort.chooseTypeSort(typeSort, flightList));

            } else {
                req.setAttribute("flightsList", flightDAO.getAllFlight());
            }

            req.setAttribute("flightStatusList", FlightStatus.getFlightStatusList());
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
            req.setAttribute("freeDrivers", userDAO.getAllUsersByListId(employmentStatusDAO.getAllFreeDrivers()));

            //car section
            //set car list
            req.setAttribute("carsList", carDAO.getAllCars());
            req.setAttribute("carBrandList", carBrandDAO.getAllCarBrand());
            req.setAttribute("carClassList", CarClass.getListCarClass());
            req.setAttribute("carTechnicalStatusList", CarTechnicalStatus.getListCarTechnicalStatus());
            req.setAttribute("carStatus", CarStatus.getListCarStatus());

            RequestDispatcher dispatcher = req.getRequestDispatcher(
                    "/WEB-INF/templates/dispatcher.jsp");
            dispatcher.forward(req, resp);
        } else {
            resp.sendRedirect("");
        }

    }

}
