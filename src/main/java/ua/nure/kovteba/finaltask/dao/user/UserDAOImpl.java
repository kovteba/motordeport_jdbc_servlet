package ua.nure.kovteba.finaltask.dao.user;

import ua.nure.kovteba.finaltask.dao.employmentstatus.EmploymentStatusDAOImpl;
import ua.nure.kovteba.finaltask.enumlist.Employment;
import ua.nure.kovteba.finaltask.util.Connect;
import ua.nure.kovteba.finaltask.entity.User;
import ua.nure.kovteba.finaltask.enumlist.Role;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Data Access Object for User entity
 * Methods: createUser, getUserByRole, getUserById, getUserByUserPhoneNumber,
 * getAllUsers, deleteUserById, getAllUsersByListId
 */
public class UserDAOImpl implements UserDAO {

    //Create logger
    private static final Logger LOG = Logger.getLogger(UserDAOImpl.class.getName());

    //set connection
    private static final Connection CONNECT = Connect.connect();

    //create statement
    private static Statement smtp;
    private static final EmploymentStatusDAOImpl EMPLOYMENT_STATUS_DAO;

    static {
        EMPLOYMENT_STATUS_DAO = new EmploymentStatusDAOImpl();
        try {
            smtp = CONNECT.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create new user
     *
     * @param user
     * @return void
     */
    @Override
    public Long createUser(User user) {
        LOG.info("Create user with role --> \"" + user.getRole().getRoleValue() + "\" ....");
        Long idNewUser = null;
        //SQL query for create new user
        String insert = "INSERT INTO " +
                "users (first_name, last_name, phone_number, role, password) " +
                "VALUES (?, ?, ?, ?, ?);";
        //Create PreparedStatement in try with resources
        try (PreparedStatement preparedStatement = CONNECT.prepareStatement(insert,
                PreparedStatement.RETURN_GENERATED_KEYS);) {
            //set value in insert string
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getPhoneNumber());
            preparedStatement.setString(4, user.getRole().getRoleValue());
            preparedStatement.setString(5, user.getPassword());
            //execute insert to table
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                idNewUser = resultSet.getLong(1);
            }
            if (user.getRole().getRoleValue().equals("DRIVER")) {
                EMPLOYMENT_STATUS_DAO.createEmploymentStatus(idNewUser, Employment.FREE);
            }
            LOG.info("New user with id == " + idNewUser + ", added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.warning("Some problem in method \"createUser\" with role --> \"" + user.getRole().getRoleValue()
                    + ", " + this.getClass());
        }
        return idNewUser;
    }

    /**
     * Get all users by role
     *
     * @param role
     * @return list all users by role
     */
    @Override
    public List<User> getUserByRole(Role role) {
        LOG.info("Find users list by role --> \"" + role.getRoleValue() + "\" ....");
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
            LOG.warning("Some problem in method \"getUserByRole\" with role --> \"" + role.getRoleValue()
                    + "\", " + this.getClass());
        }
        //return all users by role
        return allUsersByRole;
    }

    /**
     * Get user by id
     *
     * @param id
     * @return User entity
     */
    @Override
    public User getUserById(Long id) {
        LOG.info("Find user by id == " + id + " ....");
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
            LOG.warning("Some problem in method \"getUserById\" with id == " + id + ", " + this.getClass());
        }
        //return user by id
        return user;
    }

    /**
     * Fing user by phone nuber
     *
     * @param phoneNumber
     * @return
     */
    @Override
    public User getUserByUserPhoneNumber(String phoneNumber) {
        LOG.info("Find user by phone number --> " + phoneNumber + " ....");
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
            LOG.warning("Some problem in method \"getUserByUserPhoneNumber\" with phone == "
                    + phoneNumber + ", " + this.getClass());
        }
        //return user by id
        return user;
    }

    /**
     * Get list all user
     *
     * @return List<User>
     */
    @Override
    public List<User> getAllUsers() {
        LOG.info("Get all users ....");
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
            LOG.warning("Some problem in method \"getAllUsers\", " + this.getClass());
        }
        //return all users by role
        return allUsersByRole;
    }

    /**
     * Delete user by id
     *
     * @param id
     */
    @Override
    public void deleteUserById(Long id) {
        LOG.info("Delete user with id == " + id + " ....");
        String deleteUserById = "DELETE FROM users where id=" + id;
        try (Statement stmt = CONNECT.createStatement();) {
            stmt.executeUpdate(deleteUserById);
            LOG.info("User with id == " + id + ", deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.warning("Some problem in method \"deleteUserById\", " + this.getClass());
        }
    }

    /**
     * Return list user by list id
     *
     * @param idList List<Long> idList
     * @return List<User>
     */
    @Override
    public List<User> getAllUsersByListId(List<Long> idList) {
        List<User> getAllUsersByListId = new ArrayList<>();
        for (Long id : idList) {
            String slecetUsersByIdList = "SELECT * FROM users where id=" + id;
            try (ResultSet rs = smtp.executeQuery(slecetUsersByIdList);) {
                while (rs.next()) {
                    User newUser = new User();
                    newUser.setId(rs.getLong(1));
                    newUser.setFirstName(rs.getString(2));
                    newUser.setLastName(rs.getString(3));
                    newUser.setPhoneNumber(rs.getString(4));
                    newUser.setRole(Role.findRole(rs.getString(5)));
                    getAllUsersByListId.add(newUser);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                LOG.warning("Some problem in method \"getAllUsersByListId\", " + this.getClass());
            }
        }
        return getAllUsersByListId;

    }
}
