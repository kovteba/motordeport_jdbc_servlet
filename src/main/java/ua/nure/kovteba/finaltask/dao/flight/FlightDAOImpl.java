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

/**
 * Data Access Object for Flight entity
 * Methods: createFlight, getAllFlight, getAllFlightByDriver, changeFlightStatus
 */
public class FlightDAOImpl implements FlightDAO {

    //Create logger
    private static final Logger LOG = Logger.getLogger(FlightDAOImpl.class.getName());

    //set connection
    private static final Connection CONNECT = Connect.connect();

    //
    private static final Serialization SERIALIZATION = new Serialization();

    //create statement
    private static Statement smtp;

    static {
        try {
            smtp = CONNECT.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create new flight
     *
     * @param flight entity
     * @return id new Flight in database
     */
    @Override
    public Long createFlight(Flight flight) {
        LOG.info("Create flight --> " + flight.toString() + " ....");
        Long idNewRequest = null;
        //SQL query for create new flight
        String insert = "INSERT INTO " +
                "flights (flight_number, flight_status, car_id, driver_id, start_date, end_date, request) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?);";
        try (PreparedStatement preparedStatement = CONNECT.prepareStatement(insert,
                PreparedStatement.RETURN_GENERATED_KEYS);) {
            //set value in insert string
            preparedStatement.setString(1, flight.getFlightNumber());
            preparedStatement.setString(2, flight.getFlightStatus().getStatusValue());
            preparedStatement.setString(3, SERIALIZATION.entityToString(flight.getCar()));
            preparedStatement.setString(4, SERIALIZATION.entityToString(flight.getDriver()));
            preparedStatement.setString(5, flight.getStartDate().toString());
            preparedStatement.setString(6, flight.getEndDate().toString());
            preparedStatement.setString(7, String.valueOf(flight.getRequest()));
            //execute insert to table
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                idNewRequest = resultSet.getLong(1);
            }
            LOG.info("New request with id == " + idNewRequest + ", added successfully!");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            LOG.warning("Same problem in \"createFlight\" method" + this.getClass());
        }
        return idNewRequest;
    }

    /**
     * Return all flights from database
     *
     * @return List<Flight>
     */
    @Override
    public List<Flight> getAllFlight() {
        LOG.info("Get all flight ....");
        List<Flight> flightList = new ArrayList<>();
        //SQL query for create new flight
        String selectAll = "SELECT * FROM flights;";
        //Create ResultSet in try with resources
        try (ResultSet rs = CONNECT.createStatement().executeQuery(selectAll)) {
            while (rs.next()) {
                Flight newFlight = new Flight();
                newFlight.setId(rs.getLong(1));
                newFlight.setEndDate(ZonedDateTime.parse(rs.getString(2)));
                newFlight.setFlightNumber(rs.getString(3));
                newFlight.setFlightStatus(FlightStatus.findFlightStatus(rs.getString(4)));
                newFlight.setStartDate(ZonedDateTime.parse(rs.getString(5)));
                newFlight.setCar((Car) SERIALIZATION.fromString(rs.getString(6)));
                newFlight.setDriver((User) SERIALIZATION.fromString(rs.getString(7)));
                newFlight.setRequest(Long.valueOf(rs.getString(8)));
                flightList.add(newFlight);
            }
        } catch (SQLException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
            LOG.warning("Same problem in \"getAllFlight\" method" + this.getClass());

        }
        //return all flight
        return flightList;
    }

    /**
     * Delete flight by id request
     *
     * @param id request
     */
    @Override
    public void deleteFlightByIdRequest(Long id) {
        //SQL query for create new flight
        String deleteFlightByIdRequest = "DELETE FROM flights WHERE request = " + id + ";";
        try (Statement stmt = CONNECT.createStatement();) {
            stmt.executeUpdate(deleteFlightByIdRequest);
            LOG.info("User with id == " + id + ", deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.warning("Same problem in \"deleteFlightByIdRequest\" method" + this.getClass());

        }
    }

    /**
     * Finr List<Flight> by user entity
     *
     * @param driver entity for find
     * @return List<Flight>
     */
    @Override
    public List<Flight> getAllFlightByDriver(User driver) {
        LOG.info("Get all flight by driver ....");
        List<Flight> flightListByDriver = new ArrayList<>();
        //SQL query for create new flight
        String selectAll = "SELECT * FROM flights;";
        //Create ResultSet in try with resources
        try (ResultSet rs = smtp.executeQuery(selectAll);) {
            while (rs.next()) {
                User user = (User) SERIALIZATION.fromString(rs.getString(7));
                if (user != null && driver.getId().equals(user.getId())) {
                    Flight newFlight = new Flight();
                    newFlight.setId(rs.getLong(1));
                    newFlight.setEndDate(ZonedDateTime.parse(rs.getString(2)));
                    newFlight.setFlightNumber(rs.getString(3));
                    newFlight.setFlightStatus(FlightStatus.findFlightStatus(rs.getString(4)));
                    newFlight.setStartDate(ZonedDateTime.parse(rs.getString(5)));
                    newFlight.setCar((Car) SERIALIZATION.fromString(rs.getString(6)));
                    newFlight.setDriver((User) SERIALIZATION.fromString(rs.getString(7)));
                    newFlight.setRequest(Long.valueOf(rs.getString(8)));
                    flightListByDriver.add(newFlight);
                }
            }
        } catch (SQLException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
            LOG.warning("Same problem in \"getAllFlightByDriver\" method" + this.getClass());

        }
        //return all flight
        return flightListByDriver;
    }

    /**
     * Change flight status in flight by id
     * Flight status: "OPEN", "CLOSE", "IN PROGRESS", "CANCELED", "DONE"
     *
     * @param id
     * @param flightStatus
     */
    @Override
    public void changeFlightStatus(Long id, FlightStatus flightStatus) {
        LOG.info("Change flight status on --> \"" + flightStatus.getStatusValue() + "\", by id == " + id + " ....");
        //SQL query for update car_status car by id
        String changeFlightStatusById = "UPDATE flights SET flight_status = ? WHERE id = ?;";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = CONNECT.prepareStatement(changeFlightStatusById);
            preparedStmt.setString(1, flightStatus.getStatusValue());
            preparedStmt.setLong(2, id);
            preparedStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.warning("Same problem in \"changeFlightStatus\" method" + this.getClass());
        }
    }

    @Override
    public Flight getFlightById(Long id) {
        LOG.info("Get flight by ID ....");
        Flight newFlight = new Flight();
        //SQL query for create new flight
        String selectAll = "SELECT * FROM flights WHERE id = " + id + ";";
        //Create ResultSet in try with resources
        try (ResultSet rs = smtp.executeQuery(selectAll);) {
            while (rs.next()) {
                newFlight.setId(rs.getLong(1));
                newFlight.setEndDate(ZonedDateTime.parse(rs.getString(2)));
                newFlight.setFlightNumber(rs.getString(3));
                newFlight.setFlightStatus(FlightStatus.findFlightStatus(rs.getString(4)));
                newFlight.setStartDate(ZonedDateTime.parse(rs.getString(5)));
                newFlight.setCar((Car) SERIALIZATION.fromString(rs.getString(6)));
                newFlight.setDriver((User) SERIALIZATION.fromString(rs.getString(7)));
                newFlight.setRequest(Long.valueOf(rs.getString(8)));
            }
        } catch (SQLException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
            LOG.warning("Same problem in \"getFlightById\" method" + this.getClass());
        }
        //return all flight
        return newFlight;
    }
}
