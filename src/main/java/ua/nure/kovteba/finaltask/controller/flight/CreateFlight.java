package ua.nure.kovteba.finaltask.controller.flight;

import ua.nure.kovteba.finaltask.controller.user.CreateDispatcher;
import ua.nure.kovteba.finaltask.dao.car.CarDAOImpl;
import ua.nure.kovteba.finaltask.dao.employmentstatus.EmploymentStatusDAOImpl;
import ua.nure.kovteba.finaltask.dao.flight.FlightDAOImpl;
import ua.nure.kovteba.finaltask.dao.request.RequestDAOImpl;
import ua.nure.kovteba.finaltask.dao.token.TokenDAO;
import ua.nure.kovteba.finaltask.dao.token.TokenDAOImpl;
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
        name = "createFlight",
        urlPatterns = "/createFlight"
)
public class CreateFlight extends HttpServlet {

    //Create logger
    private static Logger log = Logger.getLogger(CreateFlight.class.getName());

    private static UserDAOImpl userDAO;
    private static CarDAOImpl carDAO;
    private static FlightDAOImpl flightDAO;
    private static RequestDAOImpl requestDAO;
    private static EmploymentStatusDAOImpl employmentStatusDAO;
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
            if (user.getRole().getRoleValue().equals("ADMIN") || user.getRole().getRoleValue().equals("DISPATCHER")) {

                //get approve id request
                Long idRequest = Long.valueOf(req.getParameter("idRequest"));

                //get start date and start time

                ZonedDateTime startDateTime = ZonedDateTime.of(
                        LocalDate.parse(req.getParameter("startDateInReq")),
                        LocalTime.parse(req.getParameter("startTimeInReq")),
                        ZonedDateTime.now().getZone());

                //get end date and end time
                ZonedDateTime endDateTime = ZonedDateTime.of(
                        LocalDate.parse(req.getParameter("endDateInReq")),
                        LocalTime.parse(req.getParameter("endTimeInReq")),
                        ZonedDateTime.now().getZone());

                //get id car to flight
                Long idCar = null;
                Long idDriver = Long.valueOf(req.getParameter("idDriverInReq"));

                if (req.getParameter("carValueId") != null) {
                    idCar = Long.valueOf(req.getParameter("carValueId"));
                    //create new flight
                    Flight flight = new Flight();
                    flight.setFlightNumber(req.getParameter("numbreFlightInReq"));
                    flight.setDriver(userDAO.getUserById(idDriver));
                    flight.setCar(carDAO.getCarById(idCar));
                    flight.setFlightStatus(FlightStatus.OPEN);
                    flight.setStartDate(startDateTime);
                    flight.setEndDate(endDateTime);
                    flight.setRequest(idRequest);
                    //if flightDAO return id new flight change car status and request status
                    if (flightDAO.createFlight(flight) != null) {
                        carDAO.changeCarStatus(idCar, CarStatus.BUSY);
                        requestDAO.changeStatusRequestById(idRequest, RequestStatus.CLOSED);
                        employmentStatusDAO.changeEmploymentStatus(idDriver, Employment.BUSY);
                    }
                } else {
                    resp.sendRedirect("admin");
                }
            } else {
                resp.sendRedirect("");
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
