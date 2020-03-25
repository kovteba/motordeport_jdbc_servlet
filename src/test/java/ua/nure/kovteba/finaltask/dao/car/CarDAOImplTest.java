package ua.nure.kovteba.finaltask.dao.car;

import org.junit.jupiter.api.Test;
import ua.nure.kovteba.finaltask.dao.carbrand.CarBrandDAOImpl;
import ua.nure.kovteba.finaltask.entity.Car;
import ua.nure.kovteba.finaltask.entity.CarBrand;
import ua.nure.kovteba.finaltask.enumlist.CarClass;
import ua.nure.kovteba.finaltask.enumlist.CarStatus;
import ua.nure.kovteba.finaltask.enumlist.CarTechnicalStatus;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarDAOImplTest {

    private static CarDAOImpl carDAO;

    private static CarBrandDAOImpl carBrandDAO;

    static {
        carDAO = new CarDAOImpl();
        carBrandDAO = new CarBrandDAOImpl();
    }

    @Test
    void createCar() {
        Car car = new Car();
        car.setCarBrand(carBrandDAO.getCarBrandByBrandValue("MAN"));
        car.setCarClass(CarClass.COMFORT);
        car.setCarNumber("AX6598A");
        car.setLoadCapacity(1000);
        car.setSeats(20);
        car.setLuggageCompartment(true);
        car.setAirConditioning(true);
        car.setNavigator(true);
        car.setCarTechnicalStatus(CarTechnicalStatus.GOOD);
        car.setCarStatus(CarStatus.FREE);
        Long idNewCar = carDAO.createCar(car);
    }

    @Test
    void getCarById() {
        System.out.println(carDAO.getCarById(1L).toString());
    }

    @Test
    void changeCarTechnicalStatus() {
        carDAO.changeCarTechnicalStatus(1L, CarTechnicalStatus.REPAIRS);
    }

    @Test
    void changeCarStatus() {
        carDAO.changeCarStatus(1L, CarStatus.BUSY);
    }

    @Test
    void getListCarFreeAndGood() {
    }

    @Test
    void getAllCars() {
        List<Car> list = carDAO.getAllCars();
        for (Car car : list){
            System.out.println(car.toString());
        }
    }
}