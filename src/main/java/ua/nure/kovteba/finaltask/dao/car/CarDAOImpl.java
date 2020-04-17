package ua.nure.kovteba.finaltask.dao.car;

import ua.nure.kovteba.finaltask.util.Connect;
import ua.nure.kovteba.finaltask.dao.carbrand.CarBrandDAOImpl;
import ua.nure.kovteba.finaltask.entity.Car;
import ua.nure.kovteba.finaltask.enumlist.CarClass;
import ua.nure.kovteba.finaltask.enumlist.CarStatus;
import ua.nure.kovteba.finaltask.enumlist.CarTechnicalStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Data Access Object for Car
 * Methods: createCar, getCarById, changeCarStatus, getListCarFreeAndGood
 *          getAllCars, changeCarInformation, deleteCarById
 */
public class CarDAOImpl implements CarDAO {

    //Create logger
    private static final Logger LOG = Logger.getLogger(CarDAOImpl.class.getName());

    //set connection
    private static final Connection CONNECT = Connect.connect();

    //
    private static final CarBrandDAOImpl CAR_BRAND_DAO;

    //create statement
    private static Statement smtp;

    static {
        CAR_BRAND_DAO = new CarBrandDAOImpl();
        try {
            smtp = CONNECT.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Insert new car instance to DataBase
     * @param car
     * @return id new car from database
     */
    @Override
    public Long createCar(Car car) {
        LOG.info("Create car --> " + car.toString() + "....");
        Long idNewCar = null;
        //SQL query for create new car
        String insert = "INSERT INTO " +
                "cars (seats, load_capacity, luggage_compartment, navigator, air_conditioning, " +
                "car_brand, car_number, car_class, car_technical_status, car_status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        //Create PreparedStatement in try with resources
        try (PreparedStatement preparedStatement = CONNECT.prepareStatement(insert,
                PreparedStatement.RETURN_GENERATED_KEYS);) {
            //set value in insert string
            preparedStatement.setInt(1, car.getSeats());
            preparedStatement.setInt(2, car.getLoadCapacity());
            preparedStatement.setBoolean(3, car.getLuggageCompartment());
            preparedStatement.setBoolean(4, car.getNavigator());
            preparedStatement.setBoolean(5, car.getAirConditioning());
            preparedStatement.setString(6, car.getCarBrand().getBrandName());
            preparedStatement.setString(7, car.getCarNumber());
            preparedStatement.setString(8, car.getCarClass().getClassValue());
            preparedStatement.setString(9, car.getCarTechnicalStatus().getCarTechnicalStatusValue());
            preparedStatement.setString(10, car.getCarStatus().getCarStatusValue());
            //execute insert to table
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                idNewCar = resultSet.getLong(1);
            }
            LOG.info("New car with id == " + idNewCar + ", added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.warning("Same problem in \"changeCarTechnicalStatus\" method, " + this.getClass());
        }
        return idNewCar;
    }

    /**
     * Find Car in DataBase by id
     * @param id car for find
     * @return instance car from database
     */
    @Override
    public Car getCarById(Long id) {
        LOG.info("Find car by id == " + id + " ....");
        Car car = new Car();
        //SQL query for select car by id
        String selectCarById = "SELECT * FROM cars WHERE id = '" + id + "';";
        //Create ResultSet in try with resources
        try (ResultSet rs = smtp.executeQuery(selectCarById);) {
            while (rs.next()) {
                car.setId(rs.getLong(1));
                car.setSeats(rs.getInt(2));
                car.setLoadCapacity(rs.getInt(3));
                car.setLuggageCompartment(rs.getBoolean(4));
                car.setNavigator(rs.getBoolean(5));
                car.setAirConditioning(rs.getBoolean(6));
                car.setCarBrand(CAR_BRAND_DAO.getCarBrandByBrandValue(rs.getString(7)));
                car.setCarNumber(rs.getString(8));
                car.setCarClass(CarClass.findCarClass(rs.getString(9)));
                car.setCarTechnicalStatus(CarTechnicalStatus.findCarTechnicatlStatus(rs.getString(10)));
                car.setCarStatus(CarStatus.findCarStatus(rs.getString(11)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.warning("Same problem in \"getCarById\" method with id == " + id + ", " + this.getClass());
        }
        //return car by id
        return car;
    }

    /**
     * Change technical status car by id.
     * Technical status vlues : GOOD, REPAIRS.
     * @param id car for change technical status
     * @param carTechnicalStatus new technical status
     */
    @Override
    public void changeCarTechnicalStatus(Long id, CarTechnicalStatus carTechnicalStatus) {
        LOG.info("Change car technical status on --> \""
                + carTechnicalStatus.getCarTechnicalStatusValue() + "\", by id == " + id + " ....");
        //SQL query for update car_technical_status request by id
        String changeCarTechnicelStatusById =
                "UPDATE cars SET car_technical_status = ? WHERE id = ?;";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = CONNECT.prepareStatement(changeCarTechnicelStatusById);
            preparedStmt.setString(1, carTechnicalStatus.getCarTechnicalStatusValue());
            preparedStmt.setLong(2, id);
            preparedStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.warning("Same problem in \"changeCarTechnicalStatus\" method, " + this.getClass());
        }
    }

    /**
     * Change status car by id.
     * Status vlues : FREE, BUSY.
     * @param id car for change status
     * @param carStatus new status
     */
    @Override
    public void changeCarStatus(Long id, CarStatus carStatus) {
        LOG.info("Change car status on --> \"" + carStatus.getCarStatusValue() + "\", by id == " + id + " ....");
        //SQL query for update car_status car by id
        String changeCarStatusById =
                "UPDATE cars SET car_status = ? WHERE id = ?;";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = CONNECT.prepareStatement(changeCarStatusById);
            preparedStmt.setString(1, carStatus.getCarStatusValue());
            preparedStmt.setLong(2, id);
            preparedStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.warning("Same problem in \"changeCarStatus\" method, " + this.getClass());
        }
    }

    /**
     * Find all car where status: FREE, and technical status: GOOD
     * @return List<Car>
     */
    @Override
    public List<Car> getListCarFreeAndGood() {
        LOG.info("Get car list with status --> \'FREE\', technical status --> \"GOOD\" ....");
        List<Car> listCars = new ArrayList<>();
        //SQL query for select car by id
        String selectCarFreeAndGood = "SELECT * FROM cars WHERE car_technical_status = '" + CarTechnicalStatus.GOOD.getCarTechnicalStatusValue() + "'" +
                " and car_status = '" + CarStatus.FREE.getCarStatusValue() + "';";
        //Create ResultSet in try with resources
        try (ResultSet rs = smtp.executeQuery(selectCarFreeAndGood);) {
            while (rs.next()) {
                Car car = new Car();
                car.setId(rs.getLong(1));
                car.setSeats(rs.getInt(2));
                car.setLoadCapacity(rs.getInt(3));
                car.setLuggageCompartment(rs.getBoolean(4));
                car.setNavigator(rs.getBoolean(5));
                car.setAirConditioning(rs.getBoolean(6));
                car.setCarBrand(CAR_BRAND_DAO.getCarBrandByBrandValue(rs.getString(7)));
                car.setCarNumber(rs.getString(8));
                car.setCarClass(CarClass.findCarClass(rs.getString(9)));
                car.setCarTechnicalStatus(CarTechnicalStatus.findCarTechnicatlStatus(rs.getString(10)));
                car.setCarStatus(CarStatus.findCarStatus(rs.getString(11)));
                listCars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.warning("Same problem in \"getListCarFreeAndGood\" method, " + this.getClass());
        }
        return listCars;
    }

    /**
     * Return all cars from database
     * @return
     */
    @Override
    public List<Car> getAllCars() {
        LOG.info("Get all cars ....");
        List<Car> listCars = new ArrayList<>();
        //SQL query for select car by id
        String selectCarById = "SELECT * FROM cars;";
        //Create ResultSet in try with resources
        try (ResultSet rs = smtp.executeQuery(selectCarById);) {
            while (rs.next()) {
                Car car = new Car();
                car.setId(rs.getLong(1));
                car.setSeats(rs.getInt(2));
                car.setLoadCapacity(rs.getInt(3));
                car.setLuggageCompartment(rs.getBoolean(4));
                car.setNavigator(rs.getBoolean(5));
                car.setAirConditioning(rs.getBoolean(6));
                car.setCarBrand(CAR_BRAND_DAO.getCarBrandByBrandValue(rs.getString(7)));
                car.setCarNumber(rs.getString(8));
                car.setCarClass(CarClass.findCarClass(rs.getString(9)));
                car.setCarTechnicalStatus(CarTechnicalStatus.findCarTechnicatlStatus(rs.getString(10)));
                car.setCarStatus(CarStatus.findCarStatus(rs.getString(11)));
                listCars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.warning("Same problem in \"getAllCars\" method");
        }
        return listCars;
    }

    /**
     * Change all information about car
     * @param car for change
     */
    @Override
    public void changeCarInformation(Car car) {
        LOG.info("Change car information on ....");
        //SQL query for update car_status car by id
        String changeCarInformation =
                "UPDATE cars SET car_status = ?, seats = ?, load_capacity = ?, luggage_compartment = ?," +
                        "navigator = ?, air_conditioning = ?, car_brand = ?, car_number = ?," +
                        "car_class = ?, car_technical_status = ?, car_status = ? WHERE id = ?;";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = CONNECT.prepareStatement(changeCarInformation);
            preparedStmt.setString(1, car.getCarStatus().getCarStatusValue());
            preparedStmt.setInt(2, car.getSeats());
            preparedStmt.setInt(3, car.getLoadCapacity());
            preparedStmt.setBoolean(4, car.getLuggageCompartment());
            preparedStmt.setBoolean(5, car.getNavigator());
            preparedStmt.setBoolean(6, car.getAirConditioning());
            preparedStmt.setString(7, car.getCarBrand().getBrandName());
            preparedStmt.setString(8, car.getCarNumber());
            preparedStmt.setString(9, car.getCarClass().getClassValue());
            preparedStmt.setString(10, car.getCarTechnicalStatus().getCarTechnicalStatusValue());
            preparedStmt.setString(11, car.getCarStatus().getCarStatusValue());
            preparedStmt.setLong(12, car.getId());
            preparedStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.warning("Same problem in \"changeCarInformation\" method, " + this.getClass());
        }
    }

    /**
     * Delete car from database by id
     * @param id car for delete
     */
    @Override
    public void deleteCarById(Long id) {
        LOG.info("Delete car with id == " + id + " ....");
        String deleteUserById = "DELETE FROM cars where id=" + id;
        try (Statement stmt = CONNECT.createStatement();) {
            stmt.executeUpdate(deleteUserById);
            LOG.info("Car with id == " + id + ", deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.warning("Same problem in \"deleteCarById\" method, " + this.getClass());
        }
    }
}
