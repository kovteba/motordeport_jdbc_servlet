package ua.nure.kovteba.finaltask.dao.flight;

import ua.nure.kovteba.finaltask.entity.Flight;
import ua.nure.kovteba.finaltask.entity.User;
import ua.nure.kovteba.finaltask.enumlist.FlightStatus;

import java.util.List;

public interface FlightDAO {

    Long createFlight(Flight flight);

    List<Flight> getAllFlight();

    void deleteFlightByIdRequest(Long id);

    List<Flight> getAllFlightByDriver(User driver);

    void changeFlightStatus(Long id, FlightStatus flightStatus);

    Flight getFlightById(Long id);

}
