package ua.nure.kovteba.finaltask.dao.user;

import ua.nure.kovteba.finaltask.util.Connect;
import ua.nure.kovteba.finaltask.entity.User;
import ua.nure.kovteba.finaltask.enumlist.Role;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Logger;

public class UserDAOImpl implements UserDAO {

    //Create logger
    private static Logger LOG = Logger.getLogger(UserDAOImpl.class.getName());

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

    /** Create new user
     * @param  user
     * @return void
     * */
    @Override
    public Long createUser(User user) {
        LOG.info("CRETE NEW USER WITH ROLE : " + user.getRole().getRoleValue());
        Long idNewUser = null;
        //SQL query for create new user
        String insert = "INSERT INTO " +
                "users (first_name, last_name, phone_number, role, password) " +
                "VALUES (?, ?, ?, ?, ?);";
        //Create PreparedStatement in try with resources
        try (PreparedStatement preparedStatement = conn.prepareStatement(insert,
                PreparedStatement.RETURN_GENERATED_KEYS);) {
            //set value in insert string
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getPhoneNumber());
            preparedStatement.setString(4, user.getRole().getRoleValue());
            preparedStatement.setString(5, user.getPassword());
            //execute insert to table
            preparedStatement.executeUpdate();            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()){
                idNewUser = resultSet.getLong(1);
            }
            LOG.info("NEW USER ADDED SUCCESSFULY");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idNewUser;
    }

    /**
     * Get all users by role
     * @param role
     * @return list all users by role
     */
    @Override
    public List<User> getUserByRole(Role role) {
        LOG.info("FIND USERS LIST BY ROLE : " + role.getRoleValue());
        //create return list
        List<User> allUsersByRole = new ArrayList();
        //SQL query for select users by role
        String selectAllByRole = "SELECT * FROM users " +
                "WHERE role = '" + role.getRoleValue() + "';";
        //Create ResultSet in try with resources
        try (ResultSet rs = smtp.executeQuery(selectAllByRole);) {
            while (rs.next()) {
                User newUser = new User();
                newUser.setId(rs.getLong(1));
                newUser.setFirstName(rs.getString(2));
                newUser.setLastName(rs.getString(3));
                newUser.setPhoneNumber(rs.getString(4));
                newUser.setRole(Role.findRole(rs.getString(5)));
                allUsersByRole.add(newUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //return all users by role
        return allUsersByRole;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public User getUserById(Long id) {
        LOG.info("FIND USER BY ID : " + id);
        User user = null;
        //SQL query for select users by id
        String selectUserById = "SELECT * FROM users " +
                "WHERE id = '" + id + "';";
        //Create ResultSet in try with resources
        try (ResultSet rs = smtp.executeQuery(selectUserById);) {
            while (rs.next()) {
                user = new User();
                user.setId(rs.getLong(1));
                user.setFirstName(rs.getString(2));
                user.setLastName(rs.getString(3));
                user.setPhoneNumber(rs.getString(4));
                user.setRole(Role.findRole(rs.getString(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //return user by id
        return user;
    }

    /**
     *
     * @param phoneNumber
     * @return
     */
    @Override
    public User getUserByUserPhoneNumber(String phoneNumber) {
        LOG.info("FIND USER BY PHONE NUMBER : " + phoneNumber);
        User user = null;
        //SQL query for select users by id
        String selectUserById = "SELECT * FROM users " +
                "WHERE phone_number = '" + phoneNumber + "';";
        //Create ResultSet in try with resources
        try (ResultSet rs = smtp.executeQuery(selectUserById);) {
            while (rs.next()) {
                user = new User();
                user.setId(rs.getLong(1));
                user.setFirstName(rs.getString(2));
                user.setLastName(rs.getString(3));
                user.setPhoneNumber(rs.getString(4));
                user.setRole(Role.findRole(rs.getString(5)));
                user.setPassword(rs.getString(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //return user by id
        return user;
    }

    /**
     *
     * @return
     */
    @Override
    public List<User> getAllUsers() {
        LOG.info("GET ALL USERS LIST");
        //create return list
        List<User> allUsersByRole = new ArrayList();
        //SQL query for select users by role
        String selectAll = "SELECT * FROM users;";
        //Create ResultSet in try with resources
        try (ResultSet rs = smtp.executeQuery(selectAll);) {
            while (rs.next()) {
                User newUser = new User();
                newUser.setId(rs.getLong(1));
                newUser.setFirstName(rs.getString(2));
                newUser.setLastName(rs.getString(3));
                newUser.setPhoneNumber(rs.getString(4));
                newUser.setRole(Role.findRole(rs.getString(5)));
                allUsersByRole.add(newUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //return all users by role
        return allUsersByRole;
    }

    @Override
    public void deleteUserById(Long id) {
        LOG.info("DELETE USER WITH ID : " + id);
        String deleteUserById = "DELETE FROM users where id=" + id;
        try (Statement stmt = conn.createStatement();) {
            stmt.executeUpdate(deleteUserById);
            LOG.info("USER WITH ID -> " + id + ", DELETED SUCCESSFULY");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
