package ua.nure.kovteba.finaltask.dao.request;

import ua.nure.kovteba.finaltask.util.Connect;
import ua.nure.kovteba.finaltask.entity.Request;
import ua.nure.kovteba.finaltask.entity.User;
import ua.nure.kovteba.finaltask.enumlist.CarClass;
import ua.nure.kovteba.finaltask.enumlist.RequestStatus;
import ua.nure.kovteba.finaltask.util.Serialization;

import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Logger;

public class RequestDAOImpl implements RequestDAO {

    //Create logger
    private static Logger LOG = Logger.getLogger(RequestDAOImpl.class.getName());

    //set connection
    private static Connection conn = Connect.connect();

    //
    private static Serialization serialization = new Serialization();

    //create statement
    private static Statement smtp;

    static {
        try {
            assert conn != null;
            smtp = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Insert new request in data base
     *
     * @param request
     * @return id new user in data base Long
     */
    @Override
    public Long createRequest(Request request) {
        Long idNewRequest = null;
        //SQL query for create new user
        String insert = "INSERT INTO " +
                "requests (driver_id, car_class, load_capacity, seats, luggage_compartment, air_conditioning, navigator, request_status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        //Create PreparedStatement in try with resources
        try (PreparedStatement preparedStatement = conn.prepareStatement(insert,
                PreparedStatement.RETURN_GENERATED_KEYS);) {
            //set value in insert string
            preparedStatement.setString(1, serialization.entityToString(request.getDriver()));
            preparedStatement.setString(2, request.getCarClass().getClassValue());
            preparedStatement.setInt(3, request.getLoadCapacity());
            preparedStatement.setInt(4, request.getSeats());
            preparedStatement.setBoolean(5, request.getLuggageCompartment());
            preparedStatement.setBoolean(6, request.getAirConditioning());
            preparedStatement.setBoolean(7, request.getNavigator());
            preparedStatement.setString(8, request.getRequestStatus().getRequestSatus());
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

    /**
     * Return all request by status : OPEN, CLOSED
     *
     * @param requestStatus
     * @return List<Request>
     */
    @Override
    public List<Request> getAllRequestByStatus(RequestStatus requestStatus) {
        LOG.info("GET ALL REQUEST BY STATUS : " + requestStatus.getRequestSatus());
        //create return list
        List<Request> allRequestBySatus = new ArrayList();
        //SQL query for select request by status
        String selectAllByRole = "SELECT * FROM requests WHERE request_status = '" + requestStatus + "';";
        //Create ResultSet in try with resources
        try (ResultSet rs = smtp.executeQuery(selectAllByRole);) {
            while (rs.next()) {
                Request request = installRequest(
                        rs.getLong(1),
                        rs.getString(8),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(7),
                        rs.getBoolean(5),
                        rs.getBoolean(2),
                        rs.getBoolean(6),
                        rs.getString(9));
                allRequestBySatus.add(request);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //return all users by role
        return allRequestBySatus;
    }

    /**
     * Get request by Long id
     *
     * @param id request
     * @return request
     */
    @Override
    public Request getRequestById(Long id) {
        LOG.info("GET REQUEST BY ID : " + id);
        Request request = null;
        //SQL query for select request by id
        String selectUserById = "SELECT * FROM requests " +
                "WHERE id = '" + id + "';";
        //Create ResultSet in try with resources
        try (ResultSet rs = smtp.executeQuery(selectUserById);) {
            while (rs.next()) {
                request = installRequest(
                        rs.getLong(1),
                        rs.getString(8),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(7),
                        rs.getBoolean(5),
                        rs.getBoolean(2),
                        rs.getBoolean(6),
                        rs.getString(9));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //return request by id
        return request;
    }

    /**
     * Change request status : OPEN, CLOSED
     *
     * @param id
     * @param requestStatus
     */
    @Override
    public void changeStatusRequestById(Long id, RequestStatus requestStatus) {
        LOG.info("CHENGE REQUEST STATUS ON -> " + requestStatus.getRequestSatus() + ", BY ID : " + id);
        //SQL query for update status request by id
        String changeStatusRequestById =
                "UPDATE requests SET request_status = ? WHERE id = ?;";
        //Create ResultSet in try with resources
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = conn.prepareStatement(changeStatusRequestById);
            preparedStmt.setString(1, requestStatus.getRequestSatus());
            preparedStmt.setLong(2, id);
            preparedStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Request installRequest(
            Long id, String driver, String carClass, int loadCapacity, int seats,
            Boolean luggage, Boolean air, Boolean navigator, String requestStatus) {
        Request request = new Request();
        request.setId(id);
        try {
            request.setDriver((User) serialization.fromString(driver));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        request.setCarClass(CarClass.findCarClass(carClass));
        request.setLoadCapacity(loadCapacity);
        request.setSeats(seats);
        request.setLuggageCompartment(luggage);
        request.setAirConditioning(air);
        request.setNavigator(navigator);
        request.setRequestStatus(RequestStatus.findRequestStatus(requestStatus));
        return request;
    }
}
