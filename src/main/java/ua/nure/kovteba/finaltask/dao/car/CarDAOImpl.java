package ua.nure.kovteba.finaltask.dao.car;

import ua.nure.kovteba.finaltask.util.Connect;
import ua.nure.kovteba.finaltask.dao.carbrand.CarBrandDAOImpl;
import ua.nure.kovteba.finaltask.entity.Car;
import ua.nure.kovteba.finaltask.enumlist.CarClass;
import ua.nure.kovteba.finaltask.enumlist.CarStatus;
import ua.nure.kovteba.finaltask.enumlist.CarTechnicalStatus;
import ua.nure.kovteba.finaltask.util.Serialization;

import java.sql.*;
import java.util.logging.Logger;

public class CarDAOImpl implements CarDAO {

    //Create logger
    private static Logger LOG = Logger.getLogger(CarDAOImpl.class.getName());

    //set connection
    private static Connection conn = Connect.connect();

    //
    private static Serialization serialization = new Serialization();

    //
    private static CarBrandDAOImpl carBrandDAO = new CarBrandDAOImpl();

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
    public Long createCar(Car car) {
        Long idNewCar = null;
        //SQL query for create new car
        String insert = "INSERT INTO " +
                "cars (seats, load_capacity, luggage_compartment, navigator, air_conditioning, " +
                "car_brand, car_number, car_class, car_technical_status, car_status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        //Create PreparedStatement in try with resources
        try (PreparedStatement preparedStatement = conn.prepareStatement(insert,
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
            LOG.info("NEW CAR ADDED SUCCESSFULY");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idNewCar;
    }

    @Override
    public Car getCarById(Long id) {
        LOG.info("FIND CAR BY ID");
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
                car.setCarBrand(carBrandDAO.getCarBrandByBrandValue(rs.getString(7)));
                car.setCarNumber(rs.getString(8));
                car.setCarClass(CarClass.findCarClass(rs.getString(9)));
                car.setCarTechnicalStatus(CarTechnicalStatus.findCarTechnicatlStatus(rs.getString(10)));
                car.setCarStatus(CarStatus.findCarClass(rs.getString(11)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //return car by id
        return car;
    }

    @Override
    public void changeCarTechnicalStatus(Long id, CarTechnicalStatus carTechnicalStatus) {
        LOG.info("CHANGE CAR TECHNICAL STATUS BY ID");
        //SQL query for update car_technical_status request by id
        String changeCarTechnicelStatusById =
                "UPDATE cars SET car_technical_status = ? WHERE id = ?;";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = conn.prepareStatement(changeCarTechnicelStatusById);
            preparedStmt.setString(1, carTechnicalStatus.getCarTechnicalStatusValue());
            preparedStmt.setLong(2, id);
            preparedStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void changeCarStatus(Long id, CarStatus carStatus) {
        LOG.info("CHANGE CAR STATUS BY ID");
        //SQL query for update car_status car by id
        String changeCarTechnicelStatusById =
                "UPDATE cars SET car_status = ? WHERE id = ?;";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = conn.prepareStatement(changeCarTechnicelStatusById);
            preparedStmt.setString(1, carStatus.getCarStatusValue());
            preparedStmt.setLong(2, id);
            preparedStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
