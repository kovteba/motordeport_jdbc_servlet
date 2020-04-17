package ua.nure.kovteba.finaltask.dao.carbrand;

import ua.nure.kovteba.finaltask.util.Connect;
import ua.nure.kovteba.finaltask.entity.CarBrand;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Data Access Object for CarBrand entity
 * Methods: createCarBrand, getCarBrandById, getCarBrandByBrandValue
 *          getAllCarBrand, deleteCarBrand
 */
public class CarBrandDAOImpl implements CarBrandDAO {

    //Create logger
    private static final Logger LOG = Logger.getLogger(CarBrandDAOImpl.class.getName());

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

    /**
     * Insert new car brand to database in car_brands table
     * @param carBrand new for insert
     * @return Long id new car brand in database
     */
    @Override
    public Long createCarBrand(CarBrand carBrand) {
        LOG.info("Create car brand --> " + carBrand.toString() + " ....");
        Long idNewCarBrand = null;
        //SQL query for create new user
        String insert = "INSERT INTO car_brand (brand_name) VALUES (?);";
        //Create PreparedStatement in try with resources
        try (PreparedStatement preparedStatement = CONNECT.prepareStatement(insert,
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
            LOG.warning("Same problem in \"createCarBrand\" method with brand --> "
                    + carBrand.getBrandName() + ", " + this.getClass());
        }
        return idNewCarBrand;
    }

    /**
     * Return car brand from database by id
     * @param id car brand for find
     * @return carBrand instance
     */
    @Override
    public CarBrand getCarBrandById(Long id) {
        LOG.info("Get car brand by id == " + id + " ....");
        CarBrand carBrand = new CarBrand();
        //SQL query for select request by id
        String selectCarBrandById = "SELECT * FROM car_brand WHERE id = '" + id + "';";
        //Create ResultSet in try with resources
        try (ResultSet rs = smtp.executeQuery(selectCarBrandById);) {
            while (rs.next()) {
                carBrand.setId(rs.getLong(1));
                carBrand.setBrandName(rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.warning("Same problem in \"getCarBrandById\" method with id == " + id +
                    ", " + this.getClass());
        }
        //return request by id
        return carBrand;
    }

    /**
     * Return instance CarBrand by string value car brand
     * @param brandValue String value for find
     * @return CarBrand instance
     */
    @Override
    public CarBrand getCarBrandByBrandValue(String brandValue) {
        LOG.info("Get car brand by brand value --> \'" + brandValue + "\" ....");
        //return carBrand
        CarBrand carBrand = null;
        //SQL query for select request by id
        String selectCarBrandByBrandValue = "SELECT * FROM car_brand WHERE brand_name = '" + brandValue + "';";
        //Create ResultSet in try with resources
        try (ResultSet rs = smtp.executeQuery(selectCarBrandByBrandValue);) {
            while (rs.next()) {
                //init carBrand
                carBrand = new CarBrand();
                carBrand.setId(rs.getLong(1));
                carBrand.setBrandName(rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.warning("Same problem in \"getCarBrandByBrandValue\" method with brand value == " +
                    brandValue + ", " + this.getClass());
        }
        //return request by id
        return carBrand;
    }

    /**
     * Return all car brand from database
     * @return List<CarBrand>
     */
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
            LOG.warning("Same problem in \"getAllCarBrand\" method, " + this.getClass());
        }
        return carBrandList;
    }

    /**
     * Delete car brand by id from database
     * @param id Long value for delete
     */
    @Override
    public void deleteCarBrand(Long id) {
        LOG.info("Delete car brand with id == " + id + " ....");
        String deleteUserById = "DELETE FROM car_brand where id=" + id;
        try (Statement stmt = CONNECT.createStatement();) {
            stmt.executeUpdate(deleteUserById);
            LOG.info("Car brand with id == " + id + ", deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.warning("Same problem in \"deleteCarBrand\" method, " + this.getClass());
        }
    }
}
