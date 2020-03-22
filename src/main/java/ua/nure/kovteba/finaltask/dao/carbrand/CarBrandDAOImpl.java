package ua.nure.kovteba.finaltask.dao.carbrand;

import ua.nure.kovteba.finaltask.connection.Connect;
import ua.nure.kovteba.finaltask.entity.CarBrand;
import ua.nure.kovteba.finaltask.util.Serialization;

import java.sql.*;
import java.util.logging.Logger;

public class CarBrandDAOImpl implements CarBrandDAO{

    //Create logger
    private static Logger LOG = Logger.getLogger(CarBrandDAOImpl.class.getName());

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
    public Long cteateCarBrand(CarBrand carBrand) {
        Long idNewCarBrand = null;
        //SQL query for create new user
        String insert = "INSERT INTO " +
                "car_brand (brand_name) " +
                "VALUES (?);";
        //Create PreparedStatement in try with resources
        try (PreparedStatement preparedStatement = conn.prepareStatement(insert,
                PreparedStatement.RETURN_GENERATED_KEYS);) {
            //set value in insert string
            preparedStatement.setString(1, carBrand.getBrandName());
            //execute insert to table
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                idNewCarBrand = resultSet.getLong(1);
            }
            LOG.info("new request added successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idNewCarBrand;
    }

    @Override
    public CarBrand getCarBrandById(Long id) {
        CarBrand carBrand = new CarBrand();
        //SQL query for select request by id
        String selectUserById = "SELECT * FROM car_brand WHERE id = '" + id + "';";
        //Create ResultSet in try with resources
        try (ResultSet rs = smtp.executeQuery(selectUserById);) {
            while (rs.next()) {
                carBrand.setId(rs.getLong(1));
                carBrand.setBrandName(rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //return request by id
        return carBrand;
    }

    @Override
    public CarBrand getCarBrandByBrandValue(String brandValue) {
        CarBrand carBrand = new CarBrand();
        //SQL query for select request by id
        String selectUserById = "SELECT * FROM car_brand WHERE brand_name = '" + brandValue + "';";
        //Create ResultSet in try with resources
        try (ResultSet rs = smtp.executeQuery(selectUserById);) {
            while (rs.next()) {
                carBrand.setId(rs.getLong(1));
                carBrand.setBrandName(rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //return request by id
        return carBrand;
    }
}
