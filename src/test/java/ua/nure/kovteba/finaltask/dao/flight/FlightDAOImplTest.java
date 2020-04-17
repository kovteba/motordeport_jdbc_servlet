package ua.nure.kovteba.finaltask.dao.flight;

import org.junit.jupiter.api.Test;
import ua.nure.kovteba.finaltask.dao.car.CarDAOImpl;
import ua.nure.kovteba.finaltask.dao.request.RequestDAOImpl;
import ua.nure.kovteba.finaltask.dao.user.UserDAOImpl;
import ua.nure.kovteba.finaltask.entity.Flight;
import ua.nure.kovteba.finaltask.enumlist.FlightStatus;

import java.time.ZonedDateTime;
import java.util.List;

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
        Long id = flightDAO.createFlight(flight);
        System.out.println("New flight id : " + id);
    }

    @Test
    void getAllFlight() {
        List<Flight> list = flightDAO.getAllFlight();
        for (Flight flight : list) {
            System.out.println(flight.getFlightStatus());
        }
    }

    @Test
    void deleteFlightByIdRequest() {
        flightDAO.deleteFlightByIdRequest(16L);
    }

    @Test
    void getAllFlightByDriver() {
        List<Flight> list = flightDAO.getAllFlightByDriver(userDAO.getUserById(133L));
        for (Flight flight : list) {
            System.out.println(flight.toString());
        }
    }


}