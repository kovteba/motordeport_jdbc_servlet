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
    private static Logger log = Logger.getLogger(RequestDAOImpl.class.getName());

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
        log.info("Create request --> " + request.toString() + " ....");
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
            log.info("new request with id == " + idNewRequest + ", added successfully!");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            log.warning("Some problem in method \"createRequest\", " + e.toString());
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
        log.info("Get all request by status --> \"" + requestStatus.getRequestSatus() + "\" ....");
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
            log.warning("Some problem in method \"getAllRequestByStatus\", with request status --> \'"
                    + requestStatus.getRequestSatus() + "\', " + e.toString());
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
        log.info("Get request by id == " + id + " ....");
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
            log.warning("Some problem in method \"getRequestById\" by id == " + id + ", " + e.toString());
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
        log.info("Change request status on -->  \"" + requestStatus.getRequestSatus() + "\", by id == " + id + " ....");
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
            log.warning("Some problem in method \"changeStatusRequestById\" by id == " + id + ", with request status --> " +
                    requestStatus.getRequestSatus() + ", " + e.toString());
        }
    }

    @Override
    public List<Request> getAllRequest() {
        log.info("Get all requests by ....");
        //create return list
        List<Request> allRequestBySatus = new ArrayList();
        //SQL query for select all requests
        String selectAllByRole = "SELECT * FROM requests;";
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
            log.warning("Some problem in method \"getAllRequestByStatus\"");
        }
        //return all users by role
        return allRequestBySatus;
    }

    @Override
    public List<Request> getAllRequestByDriver(User user) {
        log.info("Get all requests by user ....");
        //create return list
        List<Request> allRequestByUser = new ArrayList();
        //SQL query for select all requests
        String selectAll = "SELECT * FROM requests;";
        //Create ResultSet in try with resources
        try (ResultSet rs = smtp.executeQuery(selectAll);) {
            while (rs.next()) {
                User userDB = (User)serialization.fromString(rs.getString(8));
                if (user.getId().equals(userDB.getId())){
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
                    allRequestByUser.add(request);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            log.warning("Some problem in method \"getAllRequestByStatus\"");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //return all users by role
        return allRequestByUser;
    }

    @Override
    public List<Request> getAllRequestByDriverAndRequestStatus(User user, RequestStatus requestStatus) {
        log.info("Get all request by status --> \"" + requestStatus.getRequestSatus() + "\" ....");
        //create return list
        List<Request> allRequestBySatusAndUser = new ArrayList();
        //SQL query for select request by status
        String selectAllByDriverAndStatus = "SELECT * FROM requests WHERE request_status = '" + requestStatus + "';";
        //Create ResultSet in try with resources
        try (ResultSet rs = smtp.executeQuery(selectAllByDriverAndStatus);) {
            while (rs.next()) {
                User userDB = (User)serialization.fromString(rs.getString(8));
                if (user.getId().equals(userDB.getId())){
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
                    allRequestBySatusAndUser.add(request);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            log.warning("Some problem in method \"getAllRequestByStatus\", with request status --> \'"
                    + requestStatus.getRequestSatus() + "\', " + e.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //return all users by role
        return allRequestBySatusAndUser;
    }

    @Override
    public void deleteRequestById(Long id) {
        log.info("Delete request with id == " + id + " ....");
        String deleteRequestById = "DELETE FROM requests where id=" + id;
        try (Statement stmt = conn.createStatement();) {
            stmt.executeUpdate(deleteRequestById);
            log.info("Car with id == " + id + ", deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param id
     * @param driver
     * @param carClass
     * @param loadCapacity
     * @param seats
     * @param luggage
     * @param air
     * @param navigator
     * @param requestStatus
     * @return
     */
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
