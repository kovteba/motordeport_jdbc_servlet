package ua.nure.kovteba.finaltask.controller.flight;

import ua.nure.kovteba.finaltask.dao.car.CarDAOImpl;
import ua.nure.kovteba.finaltask.dao.flight.FlightDAOImpl;
import ua.nure.kovteba.finaltask.dao.request.RequestDAOImpl;
import ua.nure.kovteba.finaltask.dao.user.UserDAOImpl;
import ua.nure.kovteba.finaltask.entity.Flight;
import ua.nure.kovteba.finaltask.enumlist.CarStatus;
import ua.nure.kovteba.finaltask.enumlist.FlightStatus;
import ua.nure.kovteba.finaltask.enumlist.RequestStatus;

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

@WebServlet(
        name = "createFlight",
        urlPatterns = "/createFlight"
)
public class CreateFlight extends HttpServlet {

    private static UserDAOImpl userDAO;
    private static CarDAOImpl carDAO;
    private static FlightDAOImpl flightDAO;
    private static RequestDAOImpl requestDAO;

    /**
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        userDAO = new UserDAOImpl();
        carDAO = new CarDAOImpl();
        flightDAO = new FlightDAOImpl();
        requestDAO = new RequestDAOImpl();
        super.init(config);
    }

    /**
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //get approve id request
        Long idRequest = Long.valueOf(req.getParameter("idRequest"));
        //get start date and start time
        String startDate = req.getParameter("startDateInReq");
        String startTime = req.getParameter("startTimeInReq");
        ZonedDateTime startDateTime = null;
        startDateTime = ZonedDateTime.of(LocalDate.parse(startDate), LocalTime.parse(startTime), ZonedDateTime.now().getZone());
        //get end date and end time
        String endDate = req.getParameter("endDateInReq");
        String endTime = req.getParameter("endTimeInReq");
        ZonedDateTime endDateTime = ZonedDateTime.of(LocalDate.parse(endDate), LocalTime.parse(endTime), ZonedDateTime.now().getZone());
        //get id car to flight
        Long idCar = null;
        if (req.getParameter("carValueId") != null){
            idCar = Long.valueOf(req.getParameter("carValueId"));
            //create new flight
            Flight flight = new Flight();
            flight.setFlightNumber(req.getParameter("numbreFlightInReq"));
            flight.setDriver(userDAO.getUserById(Long.valueOf(req.getParameter("idDriverInReq"))));
            flight.setCar(carDAO.getCarById(idCar));
            flight.setFlightStatus(FlightStatus.OPEN);
            flight.setStartDate(startDateTime);
            flight.setEndDate(endDateTime);
            //if flightDAO return id new flight change car status and request status
            if (flightDAO.createFlight(flight) != null) {
                carDAO.changeCarStatus(idCar, CarStatus.BUSY);
                requestDAO.changeStatusRequestById(idRequest, RequestStatus.CLOSED);
            }
        }

        //get token from page
        String token = req.getParameter("token");
        //redirect to admin
        System.out.println("CREATE FLIGHT : " + token);
        resp.sendRedirect("admin?token=" + token);
    }


}
