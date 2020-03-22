package ua.nure.kovteba.finaltask.dao.carbrand;

import org.junit.jupiter.api.Test;
import ua.nure.kovteba.finaltask.entity.CarBrand;

import static org.junit.jupiter.api.Assertions.*;

class CarBrandDAOImplTest {

    private static CarBrandDAOImpl carBrandDAO = new CarBrandDAOImpl();

    @Test
    void cteateCarBrand() {
        CarBrand carBrand = new CarBrand();
        carBrand.setBrandName("Mersedes Benz");
        Long id = carBrandDAO.cteateCarBrand(carBrand);
        System.out.println(id);
    }

    @Test
    void getCarBrandById() {
        System.out.println("getCarBrandById");
        System.out.println(carBrandDAO.getCarBrandById(3L).toString());
    }

    @Test
    void getCarBrandByBrandValue() {
        System.out.println("getCarBrandByBrandValue");
        System.out.println(carBrandDAO.getCarBrandByBrandValue("MAN"));
    }
}