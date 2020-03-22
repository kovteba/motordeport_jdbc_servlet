package ua.nure.kovteba.finaltask.dao.flight;

import ua.nure.kovteba.finaltask.entity.Flight;

import java.util.List;

public interface FlightDAO {

    Long createFlight(Flight flight);

    List<Flight> getAllFlight();

}
