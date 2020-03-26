package ua.nure.kovteba.finaltask.dao.carbrand;

import ua.nure.kovteba.finaltask.util.Connect;
import ua.nure.kovteba.finaltask.entity.CarBrand;
import ua.nure.kovteba.finaltask.util.Serialization;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CarBrandDAOImpl implements CarBrandDAO {

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
    public Long createCarBrand(CarBrand carBrand) {
        LOG.info("Create car brand --> " + carBrand.toString() + " ....");
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
            LOG.info("New car brand with id == " + idNewCarBrand + ", added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.warning("Same problem in \"cteateCarBrand\" method with brand --> " + carBrand.getBrandName());
        }
        return idNewCarBrand;
    }

    @Override
    public CarBrand getCarBrandById(Long id) {
        LOG.info("Get car brand by id == " + id + " ....");
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
            LOG.warning("Same problem in \"getCarBrandById\" method with id == " + id);
        }
        //return request by id
        return carBrand;
    }

    @Override
    public CarBrand getCarBrandByBrandValue(String brandValue) {
        LOG.info("Get car brand by brand value --> \'" + brandValue + "\" ....");
        //return carBrand
        CarBrand carBrand = null;
        //SQL query for select request by id
        String selectUserById = "SELECT * FROM car_brand WHERE brand_name = '" + brandValue + "';";
        //Create ResultSet in try with resources
        try (ResultSet rs = smtp.executeQuery(selectUserById);) {
            while (rs.next()) {
                //init carBrand
                carBrand = new CarBrand();
                carBrand.setId(rs.getLong(1));
                carBrand.setBrandName(rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.warning("Same problem in \"getCarBrandByBrandValue\" method");
        }
        //return request by id
        return carBrand;
    }

    @Override
    public List<CarBrand> getAllCarBrand() {
        LOG.info("Get all car brand ....");
        //set result list
        List<CarBrand> carBrandList = null;
        //SQL query for return all car brand
        String getAllCarBrand = "SELECT * FROM car_brand";
        //Create ResultSet in try with resources
        try (ResultSet rs = smtp.executeQuery(getAllCarBrand);) {
            carBrandList = new ArrayList<>();
            while (rs.next()) {
                //init carBrand
                CarBrand carBrand = new CarBrand();
                carBrand.setId(rs.getLong(1));
                carBrand.setBrandName(rs.getString(2));
                carBrandList.add(carBrand);

            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.warning("Same problem in \"getAllCarBrand\" method");
        }
        return carBrandList;
    }

    @Override
    public void deleteCarBrand(Long id) {
        LOG.info("Delete car brand with id == " + id + " ....");
        String deleteUserById = "DELETE FROM car_brand where id=" + id;
        try (Statement stmt = conn.createStatement();) {
            stmt.executeUpdate(deleteUserById);
            LOG.info("Car brand with id == " + id + ", deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
