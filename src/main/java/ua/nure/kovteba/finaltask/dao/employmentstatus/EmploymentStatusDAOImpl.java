package ua.nure.kovteba.finaltask.dao.employmentstatus;

import ua.nure.kovteba.finaltask.entity.EmploymentStatus;
import ua.nure.kovteba.finaltask.enumlist.Employment;
import ua.nure.kovteba.finaltask.util.Connect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class EmploymentStatusDAOImpl implements EmploymentStatusDAO {

    //Create logger
    private static Logger log = Logger.getLogger(EmploymentStatusDAOImpl.class.getName());

    //set connection
    private static Connection conn = Connect.connect();

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
    public Long createEmploymentStatus(Long idDriver, Employment employmentValue) {
        log.info("Create EmploymentStatus with idDriver == " + idDriver + ", with employmentValue --> \'" + employmentValue.getEmploymentStatusValue() + "\" ....");
        Long idNewEmploymentStatus = null;
        //SQL query for create new user
        String insert = "INSERT INTO " +
                "employment_status (id_driver, value_employment) " +
                "VALUES (?, ?);";
        //Create PreparedStatement in try with resources
        try (PreparedStatement preparedStatement = conn.prepareStatement(insert,
                PreparedStatement.RETURN_GENERATED_KEYS);) {
            //set value in insert string
            preparedStatement.setLong(1, idDriver);
            preparedStatement.setString(2, employmentValue.getEmploymentStatusValue());
            //execute insert to table
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                idNewEmploymentStatus = resultSet.getLong(1);
            }
            log.info("New user with id == " + idNewEmploymentStatus + "in EmploymentStatus added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            log.warning("Some problem in method \"createEmploymentStatus\" with idDriver == " + idDriver + ", with value--> \"" + employmentValue.getEmploymentStatusValue()
                    + ", " + e.toString());
        }
        return idNewEmploymentStatus;
    }

    @Override
    public void changeEmploymentStatus(Long idDriver, Employment employmentValue) {
        log.info("Change employmentStatus on --> \"" + employmentValue.getEmploymentStatusValue() + "\", by id == " + idDriver + " ....");
        //SQL query for update car_status car by id
        String changeEmploymentStatusById =
                "UPDATE employment_status SET value_employment = ? WHERE id_driver = ?;";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = conn.prepareStatement(changeEmploymentStatusById);
            preparedStmt.setString(1, employmentValue.getEmploymentStatusValue());
            preparedStmt.setLong(2, idDriver);
            preparedStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            log.warning("Same problem in \"changeEmploymentStatus\" method in " + this.getClass());
        }
    }

    @Override
    public List<Long> getAllFreeDrivers() {
        log.info("Get all free drivers ....");
        //create return list
        List<Long> allIdFreeDrivers = new ArrayList();
        //SQL query for select all requests
        String selectAllByRole = "SELECT * FROM employment_status WHERE value_employment = 'FREE';";
        //Create ResultSet in try with resources
        try (ResultSet rs = smtp.executeQuery(selectAllByRole);) {
            while (rs.next()) {
                Long id = rs.getLong(2);
                allIdFreeDrivers.add(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            log.warning("Some problem in method \"getAllFreeDrivers\"");
        }
        //return all users by role
        return allIdFreeDrivers;
    }

    @Override
    public void deleteEmploymentStatusByDriverId(Long id) {
        log.info("Delete employment status with driver id == " + id + " ....");
        String deleteUserById = "DELETE FROM employment_status where id_driver =" + id;
        try (Statement stmt = conn.createStatement();) {
            stmt.executeUpdate(deleteUserById);
            log.info("Employment status with idDriver == " + id + ", deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<EmploymentStatus> getAllValueEmployment() {
        log.info("Get all drivers ....");
        //create return list
        List<EmploymentStatus> allValueEmployment = new ArrayList();
        //SQL query for select all requests
        String selectAll = "SELECT * FROM employment_status;";
        //Create ResultSet in try with resources
        try (ResultSet rs = smtp.executeQuery(selectAll);) {
            while (rs.next()) {
                EmploymentStatus employmentStatus = new EmploymentStatus();
                employmentStatus.setId(rs.getLong(1));
                employmentStatus.setIdDriver(rs.getLong(2));
                employmentStatus.setValue(rs.getString(3));
                allValueEmployment.add(employmentStatus);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            log.warning("Some problem in method \"getAllFreeDrivers\"");
        }
        //return all users by role
        return allValueEmployment;
    }
}
