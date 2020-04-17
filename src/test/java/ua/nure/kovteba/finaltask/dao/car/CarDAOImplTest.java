package ua.nure.kovteba.finaltask.dao.car;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import ua.nure.kovteba.finaltask.dao.carbrand.CarBrandDAOImpl;
import ua.nure.kovteba.finaltask.entity.Car;
import ua.nure.kovteba.finaltask.enumlist.CarClass;
import ua.nure.kovteba.finaltask.enumlist.CarStatus;
import ua.nure.kovteba.finaltask.enumlist.CarTechnicalStatus;

import java.util.List;

class CarDAOImplTest {

    private static final CarDAOImpl CAR_DAO;

    private static final CarBrandDAOImpl CAR_BRAND_DAO;

    private static Long idNewCar;

    static {
        CAR_DAO = new CarDAOImpl();
        CAR_BRAND_DAO = new CarBrandDAOImpl();
    }

    @Test
    void createCar() {
        Car car = new Car();
        car.setCarBrand(CAR_BRAND_DAO.getCarBrandByBrandValue("MAN"));
        car.setCarClass(CarClass.COMFORT);
        car.setCarNumber("AX6138A");
        car.setLoadCapacity(1000);
        car.setSeats(20);
        car.setLuggageCompartment(true);
        car.setAirConditioning(true);
        car.setNavigator(false);
        car.setCarTechnicalStatus(CarTechnicalStatus.GOOD);
        car.setCarStatus(CarStatus.FREE);
        idNewCar = CAR_DAO.createCar(car);
        System.out.println("New id from DB : " + idNewCar);
    }

    @Test
    void getCarById() {
        System.out.println(CAR_DAO.getCarById(26L).toString() + "\n");
    }

    @Test
    void changeCarTechnicalStatus() {
        System.out.println("Old technical status : " +
                CAR_DAO.getCarById(26L).getCarTechnicalStatus().getCarTechnicalStatusValue());
        CAR_DAO.changeCarTechnicalStatus(26L, CarTechnicalStatus.REPAIRS);
        System.out.println("New technical status : " +
                CAR_DAO.getCarById(26L).getCarTechnicalStatus().getCarTechnicalStatusValue()
                + "\n");
    }

    @Test
    void changeCarStatus() {
        System.out.println("Old status : " + CAR_DAO.getCarById(26L).getCarStatus().getCarStatusValue());
        CAR_DAO.changeCarStatus(26L, CarStatus.BUSY);
        System.out.println("New status : " + CAR_DAO.getCarById(26L).getCarStatus().getCarStatusValue() + "\n");
    }

    @Test
    void getListCarFreeAndGood() {
        System.out.println("List cars with values FREE status and GOOD technical status");
        for (Car car : CAR_DAO.getListCarFreeAndGood()) {
            System.out.println(car.getId() + " "
                    + car.getCarStatus().getCarStatusValue() + " "
                    + car.getCarTechnicalStatus().getCarTechnicalStatusValue());
        }
        System.out.println("\n");
    }

    @Test
    void getAllCars() {
        System.out.println("List all cars");
        for (Car car : CAR_DAO.getAllCars()) {
            System.out.println(car.toString());
        }
        System.out.println("\n");
    }

    @Test
    void changeCarInformation() {
        System.out.println("Change car information");
        Car car = CAR_DAO.getCarById(26L);
        System.out.println("Old value : " + car.toString());
        car.setCarBrand(CAR_BRAND_DAO.getCarBrandByBrandValue("MAN"));
        car.setCarClass(CarClass.ECONOMY);
        car.setCarNumber("DC987H");
        car.setLoadCapacity(500);
        car.setSeats(10);
        car.setLuggageCompartment(false);
        car.setAirConditioning(false);
        car.setNavigator(false);
        car.setCarTechnicalStatus(CarTechnicalStatus.GOOD);
        car.setCarStatus(CarStatus.FREE);
        CAR_DAO.changeCarInformation(car);
        System.out.println("Old value : " + CAR_DAO.getCarById(26L).toString() + "\n");

    }

    @Test
    void deleteCarById() {
        System.out.println("Delete car by id");
        CAR_DAO.deleteCarById(26L);
    }
}