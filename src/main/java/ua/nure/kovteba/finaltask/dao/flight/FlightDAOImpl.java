package ua.nure.kovteba.finaltask.dao.flight;

import ua.nure.kovteba.finaltask.entity.Car;
import ua.nure.kovteba.finaltask.entity.User;
import ua.nure.kovteba.finaltask.enumlist.FlightStatus;
import ua.nure.kovteba.finaltask.util.Connect;
import ua.nure.kovteba.finaltask.entity.Flight;
import ua.nure.kovteba.finaltask.util.Serialization;

import java.io.IOException;
import java.sql.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class FlightDAOImpl implements FlightDAO {

    //Create logger
    private static Logger log = Logger.getLogger(FlightDAOImpl.class.getName());

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
        log.info("Create flight --> " + flight.toString() + " ....");
        Long idNewRequest = null;
        //SQL query for create new flight
        String insert = "INSERT INTO " +
                "flights (flight_number, flight_status, car_id, driver_id, start_date, end_date, request) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?);";
        try (PreparedStatement preparedStatement = conn.prepareStatement(insert,
                PreparedStatement.RETURN_GENERATED_KEYS);) {
            //set value in insert string
            preparedStatement.setString(1, flight.getFlightNumber());
            preparedStatement.setString(2, flight.getFlightStatus().getStatusValue());
            preparedStatement.setString(3, serialization.entityToString(flight.getCar()));
            preparedStatement.setString(4, serialization.entityToString(flight.getDriver()));
            preparedStatement.setString(5, flight.getStartDate().toString());
            preparedStatement.setString(6, flight.getEndDate().toString());
            preparedStatement.setString(7, String.valueOf(flight.getRequest()));
            //execute insert to table
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                idNewRequest = resultSet.getLong(1);
            }
            log.info("New request with id == " + idNewRequest + ", added successfully!");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            log.warning("Same problem in \"createFlight\" method");
        }
        return idNewRequest;
    }

    @Override
    public List<Flight> getAllFlight() {
        log.info("Get all flight ....");
        List<Flight> flightList = new ArrayList<>();
        //SQL query for create new flight
        String selectAll = "SELECT * FROM flights;";
        //Create ResultSet in try with resources
        try (ResultSet rs = smtp.executeQuery(selectAll);) {
            while (rs.next()) {
                Flight newFlight = new Flight();
                newFlight.setId(rs.getLong(1));
                newFlight.setEndDate(ZonedDateTime.parse(rs.getString(2)));
                newFlight.setFlightNumber(rs.getString(3));
                newFlight.setFlightStatus(FlightStatus.findFlightStatus(rs.getString(4)));
                newFlight.setStartDate(ZonedDateTime.parse(rs.getString(5)));
                newFlight.setCar((Car) serialization.fromString(rs.getString(6)));
                newFlight.setDriver((User) serialization.fromString(rs.getString(7)));
                newFlight.setRequest(Long.valueOf(rs.getString(8)));
                flightList.add(newFlight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //return all flight
        return flightList;
    }

    @Override
    public void deleteFlightByIdRequest(Long id) {
        //SQL query for create new flight
        String deleteFlightByIdRequest = "DELETE FROM flights WHERE request = " + id + ";";
        try (Statement stmt = conn.createStatement();) {
            stmt.executeUpdate(deleteFlightByIdRequest);
            log.info("User with id == " + id + ", deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Flight> getAllFlightByDriver(User driver) {
        log.info("Get all flight ....");
        List<Flight> flightListByDriver = new ArrayList<>();
        //SQL query for create new flight
        String selectAll = "SELECT * FROM flights;";
        //Create ResultSet in try with resources
        try (ResultSet rs = smtp.executeQuery(selectAll);) {
            while (rs.next()) {
                User user = (User) serialization.fromString(rs.getString(7));
                if (driver.getId().equals(user.getId())){
                    Flight newFlight = new Flight();
                    newFlight.setId(rs.getLong(1));
                    newFlight.setEndDate(ZonedDateTime.parse(rs.getString(2)));
                    newFlight.setFlightNumber(rs.getString(3));
                    newFlight.setFlightStatus(FlightStatus.findFlightStatus(rs.getString(4)));
                    newFlight.setStartDate(ZonedDateTime.parse(rs.getString(5)));
                    newFlight.setCar((Car) serialization.fromString(rs.getString(6)));
                    newFlight.setDriver((User) serialization.fromString(rs.getString(7)));
                    newFlight.setRequest(Long.valueOf(rs.getString(8)));
                    flightListByDriver.add(newFlight);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //return all flight
        return flightListByDriver;
    }
}
