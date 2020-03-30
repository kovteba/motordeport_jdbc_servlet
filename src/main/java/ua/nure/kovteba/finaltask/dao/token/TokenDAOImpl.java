package ua.nure.kovteba.finaltask.dao.token;

import ua.nure.kovteba.finaltask.dao.user.UserDAOImpl;
import ua.nure.kovteba.finaltask.entity.Token;
import ua.nure.kovteba.finaltask.util.Connect;
import ua.nure.kovteba.finaltask.util.Serialization;

import java.sql.*;
import java.util.UUID;
import java.util.logging.Logger;

public class TokenDAOImpl implements TokenDAO {

    //Create logger
    private static Logger LOG = Logger.getLogger(TokenDAOImpl.class.getName());

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

    private static UserDAOImpl userDAO = new UserDAOImpl();

    @Override
    public String createToken(Long id) {
        if (findUserIdInTokens(id)){
            return update(id);
        } else {
            return create(id);
        }
    }

    @Override
    public Token getTokenByToken(String stringToken) {
        Token token = null;
        String findTokenByToken = "SELECT * FROM tokens WHERE token = '" + stringToken + "';";
        try (ResultSet rs = smtp.executeQuery(findTokenByToken);) {
            while (rs.next()){
                token = new Token();
                token.setId(rs.getLong(1));
                token.setToken(rs.getString(2));
                token.setUser(rs.getLong(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return token;
    }

    @Override
    public void deleteTokenByToken(String token) {
        LOG.info("Delete token where token == " + token + " ....");
        String deleteTokenByValue = "DELETE FROM tokens WHERE token='" + token + "'";
        try (Statement stmt = conn.createStatement();) {
            stmt.executeUpdate(deleteTokenByValue);
            LOG.info("Token == " + token + ", deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String generateToken(){
        return UUID.randomUUID().toString();
    }

    private Boolean findUserIdInTokens(Long id){
        Boolean result = false;
        String findId = "SELECT * FROM tokens WHERE user = " + id + ";";
        try (ResultSet rs = smtp.executeQuery(findId);) {
            result = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String create(Long id){
        String token = generateToken();
        //SQL query for create new user
        String insert = "INSERT INTO " +
                "tokens (token, user) " +
                "VALUES (?, ?);";
        //Create PreparedStatement in try with resources
        try (PreparedStatement preparedStatement = conn.prepareStatement(insert);) {
            //set value in insert string
            preparedStatement.setString(1, token);
            preparedStatement.setLong(2, id);
            //execute insert to table
            preparedStatement.executeUpdate();
            LOG.info("new token added successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return token;
    }

    private String update(Long id){
        String token = generateToken();
        //SQL query for update car_technical_status request by id
        String changeToken = "UPDATE tokens SET token = ? WHERE user = ?;";
        try (PreparedStatement preparedStmt = conn.prepareStatement(changeToken);) {
            preparedStmt.setString(1, token);
            preparedStmt.setLong(2, id);
            preparedStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return token;
    }

}
