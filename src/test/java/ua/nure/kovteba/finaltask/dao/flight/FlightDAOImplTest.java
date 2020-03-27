package ua.nure.kovteba.finaltask.dao.flight;

import org.junit.jupiter.api.Test;
import ua.nure.kovteba.finaltask.dao.car.CarDAOImpl;
import ua.nure.kovteba.finaltask.dao.request.RequestDAOImpl;
import ua.nure.kovteba.finaltask.dao.user.UserDAOImpl;
import ua.nure.kovteba.finaltask.entity.Flight;
import ua.nure.kovteba.finaltask.enumlist.FlightStatus;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlightDAOImplTest {

    UserDAOImpl userDAO = new UserDAOImpl();
    RequestDAOImpl requestDAO = new RequestDAOImpl();
    FlightDAOImpl flightDAO = new FlightDAOImpl();
    CarDAOImpl carDAO = new CarDAOImpl();

    @Test
    void createFlight() {
        Flight flight = new Flight();
        flight.setFlightNumber("2545");
        flight.setDriver(userDAO.getUserById(2L));
        flight.setCar(carDAO.getCarById(2L));
        flight.setFlightStatus(FlightStatus.OPEN);
        flight.setStartDate(ZonedDateTime.now());
        flight.setEndDate(ZonedDateTime.now().plusDays(2));
        flight.setRequest(16L);
        flightDAO.createFlight(flight);

    }

    @Test
    void getAllFlight() {
        List<Flight> list = flightDAO.getAllFlight();
        for (Flight flight : list){
            System.out.println(flight.toString());
            System.out.println(flight.getStartDate().toLocalDate());
        }


    }

    @Test
    void deleteFlightByIdRequest() {
        flightDAO.deleteFlightByIdRequest(16L);
    }
}