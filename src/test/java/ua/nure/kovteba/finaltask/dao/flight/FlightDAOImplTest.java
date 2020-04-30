package ua.nure.kovteba.finaltask.dao.flight;

import org.junit.jupiter.api.Test;
import ua.nure.kovteba.finaltask.dao.car.CarDAO;
import ua.nure.kovteba.finaltask.dao.car.CarDAOImpl;
import ua.nure.kovteba.finaltask.dao.user.UserDAO;
import ua.nure.kovteba.finaltask.dao.user.UserDAOImpl;
import ua.nure.kovteba.finaltask.entity.Flight;
import ua.nure.kovteba.finaltask.enumlist.FlightStatus;

import java.time.ZonedDateTime;
import java.util.List;

class FlightDAOImplTest {

    private static final UserDAO USER_DAO;
    private static final FlightDAO FLIGHT_DAO;
    private static final CarDAO CAR_DAO;

    static {
        USER_DAO = new UserDAOImpl();
        FLIGHT_DAO = new FlightDAOImpl();
        CAR_DAO = new CarDAOImpl();
    }

    @Test
    void createFlight() {
        Flight flight = new Flight();
        flight.setFlightNumber("2545");
        flight.setDriver(USER_DAO.getUserById(2L));
        flight.setCar(CAR_DAO.getCarById(2L));
        flight.setFlightStatus(FlightStatus.OPEN);
        flight.setStartDate(ZonedDateTime.now());
        flight.setEndDate(ZonedDateTime.now().plusDays(2));
        flight.setRequest(16L);
        Long id = FLIGHT_DAO.createFlight(flight);
        System.out.println("New flight id : " + id);
    }

    @Test
    void getAllFlight() {
        List<Flight> list = FLIGHT_DAO.getAllFlight();
        for (Flight flight : list) {
            System.out.println(flight.toString());
        }
    }

    @Test
    void deleteFlightByIdRequest() {
        FLIGHT_DAO.deleteFlightByIdRequest(16L);
    }

    @Test
    void getAllFlightByDriver() {
        List<Flight> list = FLIGHT_DAO.getAllFlightByDriver(USER_DAO.getUserById(151L));
        for (Flight flight : list) {
            System.out.println(flight.toString());
        }
    }


    @Test
    void changeFlightStatus() {
        FLIGHT_DAO.changeFlightStatus(35L, FlightStatus.findFlightStatus("OPEN"));
    }

    @Test
    void getFlightById() {
        Flight flight = FLIGHT_DAO.getFlightById(40L);
        System.out.println(flight.toString());
    }
}