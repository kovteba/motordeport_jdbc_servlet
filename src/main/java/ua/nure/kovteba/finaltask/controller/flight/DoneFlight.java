package ua.nure.kovteba.finaltask.controller.flight;

import ua.nure.kovteba.finaltask.dao.car.CarDAO;
import ua.nure.kovteba.finaltask.dao.car.CarDAOImpl;
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
import ua.nure.kovteba.finaltask.entity.User;
import ua.nure.kovteba.finaltask.enumlist.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.logging.Logger;

@WebServlet(
        name = "doneFlight",
        urlPatterns = "/doneFlight"
)
public class DoneFlight extends HttpServlet {

    //Create logger
    private static Logger log = Logger.getLogger(CreateFlight.class.getName());

    private static UserDAO userDAO;
    private static CarDAO carDAO;
    private static FlightDAO flightDAO;
    private static RequestDAO requestDAO;
    private static EmploymentStatusDAO employmentStatusDAO;
    private static TokenDAO tokenDAO;


    /**
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userDAO = new UserDAOImpl();
        carDAO = new CarDAOImpl();
        flightDAO = new FlightDAOImpl();
        requestDAO = new RequestDAOImpl();
        employmentStatusDAO = new EmploymentStatusDAOImpl();
        tokenDAO = new TokenDAOImpl();
    }

    /**
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userToken = "0";

        if (req.getSession().getAttribute("userToken") != null) {
            userToken = String.valueOf(req.getSession().getAttribute("userToken"));
        }

        log.info("user token session--> " + userToken + ", class: " + this.getClass());

        User user = null;
        if (!userToken.equals("0")) {
            user = userDAO.getUserById(tokenDAO.getTokenByToken(userToken).getUser());
        }

        if (user != null) {
            if (user.getRole().getRoleValue().equals("ADMIN") || user.getRole().getRoleValue().equals("DISPATCHER")
                    || user.getRole().getRoleValue().equals("DRIVER")) {

                Long idCarInFlight = Long.valueOf(req.getParameter("idCarInFlight"));
                Long idFinishFlight = Long.valueOf(req.getParameter("idFinishFlight"));
                User driver = flightDAO.getFlightById(idFinishFlight).getDriver();
                if (req.getParameter("carTechnicalStatusValueAfterFlight") != null) {
                    String technicalStatus = req.getParameter("carTechnicalStatusValueAfterFlight");
                    carDAO.changeCarStatus(idCarInFlight, CarStatus.FREE);
                    carDAO.changeCarTechnicalStatus(idCarInFlight, CarTechnicalStatus.findCarTechnicatlStatus(technicalStatus));
                    flightDAO.changeFlightStatus(idFinishFlight, FlightStatus.DONE);
                    employmentStatusDAO.changeEmploymentStatus(driver.getId(), Employment.FREE);
                }
            }

        } else {
            resp.sendRedirect("");
        }

        if (userToken.equals("0")){
            resp.sendRedirect("");
        } else if (user.getRole().getRoleValue().equals("ADMIN")){
            resp.sendRedirect("admin");
        } else if (user.getRole().getRoleValue().equals("DRIVER")){
            resp.sendRedirect("driver");
        } else if (user.getRole().getRoleValue().equals("DISPATCHER")){
            resp.sendRedirect("dispatcher");
        }

    }

}
