package ua.nure.kovteba.finaltask.dao.flight;

import org.junit.jupiter.api.Test;
import ua.nure.kovteba.finaltask.dao.request.RequestDAOImpl;
import ua.nure.kovteba.finaltask.dao.user.UserDAOImpl;
import ua.nure.kovteba.finaltask.entity.Flight;

import static org.junit.jupiter.api.Assertions.*;

class FlightDAOImplTest {

    UserDAOImpl userDAO = new UserDAOImpl();
    RequestDAOImpl requestDAO = new RequestDAOImpl();
    FlightDAOImpl flightDAO = new FlightDAOImpl();

    @Test
    void createFlight() {

    }

    @Test
    void getAllFlight() {
    }
}