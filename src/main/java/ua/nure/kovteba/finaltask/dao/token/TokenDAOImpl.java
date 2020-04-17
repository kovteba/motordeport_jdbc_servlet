package ua.nure.kovteba.finaltask.dao.token;

import ua.nure.kovteba.finaltask.dao.user.UserDAOImpl;
import ua.nure.kovteba.finaltask.entity.Token;
import ua.nure.kovteba.finaltask.util.Connect;

import java.sql.*;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * Data Access Object for Token entity
 * Methods: createToken, getTokenByToken, deleteTokenByToken
 */
public class TokenDAOImpl implements TokenDAO {

    //Create logger
    private static final Logger LOG = Logger.getLogger(TokenDAOImpl.class.getName());

    //set connection
    private static final Connection CONNECT = Connect.connect();

    //create statement
    private static Statement smtp;

    static {
        try {
            smtp = CONNECT.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static UserDAOImpl userDAO = new UserDAOImpl();

    /**
     * Create token for user by id
     * Find token for user by id change token
     * If token for user not exist make new insert
     *
     * @param id
     * @return String token type UUID
     */
    @Override
    public String createToken(Long id) {
        if (findUserIdInTokens(id)) {
            return update(id);
        } else {
            return create(id);
        }
    }

    /**
     * Return token by token value
     *
     * @param stringToken
     * @return Token entity
     */
    @Override
    public Token getTokenByToken(String stringToken) {
        LOG.info("Get token where token == " + stringToken + " ....");
        Token token = null;
        String findTokenByToken = "SELECT * FROM tokens WHERE token = '" + stringToken + "';";
        try (ResultSet rs = smtp.executeQuery(findTokenByToken);) {
            while (rs.next()) {
                token = new Token();
                token.setId(rs.getLong(1));
                token.setToken(rs.getString(2));
                token.setUser(rs.getLong(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.warning("Some problem in method \"getTokenByToken\", " + this.getClass());
        }
        return token;
    }

    /**
     * Delete record by token value
     *
     * @param token
     */
    @Override
    public void deleteTokenByToken(String token) {
        LOG.info("Delete token where token == " + token + " ....");
        String deleteTokenByValue = "DELETE FROM tokens WHERE token='" + token + "'";
        try (Statement stmt = CONNECT.createStatement();) {
            stmt.executeUpdate(deleteTokenByValue);
            LOG.info("Token == " + token + ", deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.warning("Some problem in method \"deleteTokenByToken\", " + this.getClass());
        }
    }

    /**
     * helper method
     * Generete new UUID
     *
     * @return String new UUID
     */
    private String generateToken() {
        return UUID.randomUUID().toString();
    }

    /**
     * Test fond in table user by id
     *
     * @param id
     * @return boolean true if user already exist else false
     */
    private Boolean findUserIdInTokens(Long id) {
        Boolean result = false;
        String findId = "SELECT * FROM tokens WHERE user = " + id + ";";
        try (ResultSet rs = smtp.executeQuery(findId);) {
            result = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.warning("Some problem in method \"findUserIdInTokens\", " + this.getClass());

        }
        return result;
    }

    /**
     * Helper method create new record in tokens table
     *
     * @param id Long user
     * @return String token
     */
    private String create(Long id) {
        String token = generateToken();
        //SQL query for create new user
        String insert = "INSERT INTO tokens (token, user) VALUES (?, ?);";
        //Create PreparedStatement in try with resources
        try (PreparedStatement preparedStatement = CONNECT.prepareStatement(insert);) {
            //set value in insert string
            preparedStatement.setString(1, token);
            preparedStatement.setLong(2, id);
            //execute insert to table
            preparedStatement.executeUpdate();
            LOG.info("new token added successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.warning("Some problem in method \"create\", " + this.getClass());
        }
        return token;
    }

    /**
     * Update token from database by user id
     *
     * @param id
     * @return
     */
    private String update(Long id) {
        String token = generateToken();
        //SQL query for update car_technical_status request by id
        String changeToken = "UPDATE tokens SET token = ? WHERE user = ?;";
        try (PreparedStatement preparedStmt = CONNECT.prepareStatement(changeToken);) {
            preparedStmt.setString(1, token);
            preparedStmt.setLong(2, id);
            preparedStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.warning("Some problem in method \"update\", " + this.getClass());
        }
        return token;
    }

}
