package ua.nure.kovteba.finaltask.dao.flight;

import ua.nure.kovteba.finaltask.util.Connect;
import ua.nure.kovteba.finaltask.entity.Flight;
import ua.nure.kovteba.finaltask.util.Serialization;

import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.logging.Logger;

public class FlightDAOImpl implements FlightDAO {

    //Create logger
    private static Logger LOG = Logger.getLogger(FlightDAOImpl.class.getName());

    //set connection
    private static Connection conn = Connect.connect();

    //
    private static Serialization serialization = new Serialization();

    //create statement
    private static Statement smtp;

    static {
        try {
            smtp = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Long createFlight(Flight flight) {
        Long idNewRequest = null;
        //SQL query for create new user
        String insert = "INSERT INTO " +
                "flights (flight_number, flight_status, car_id, driver_id, start_date, end_date) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        try (PreparedStatement preparedStatement = conn.prepareStatement(insert,
                PreparedStatement.RETURN_GENERATED_KEYS);) {
            //set value in insert string
            preparedStatement.setInt(1, flight.getFlightNumber());
            preparedStatement.setString(2, flight.getFlightStatus().getStatusValue());
            preparedStatement.setString(3, serialization.entityToString(flight.getCar()));
            preparedStatement.setString(4, serialization.entityToString(flight.getDriver()));
            preparedStatement.setString(5, flight.getStartDate().toString());
            preparedStatement.setString(5, flight.getEndDate().toString());
            //execute insert to table
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                idNewRequest = resultSet.getLong(1);
            }
            LOG.info("new request added successfully");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return idNewRequest;
    }

    @Override
    public List<Flight> getAllFlight() {
        return null;
    }
}
