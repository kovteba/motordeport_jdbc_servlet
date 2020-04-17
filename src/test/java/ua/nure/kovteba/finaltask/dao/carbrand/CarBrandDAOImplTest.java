package ua.nure.kovteba.finaltask.dao.carbrand;

import org.junit.jupiter.api.Test;
import ua.nure.kovteba.finaltask.entity.CarBrand;

class CarBrandDAOImplTest {

    private static final CarBrandDAOImpl CAR_BRAND_DAO;

    static {
        CAR_BRAND_DAO = new CarBrandDAOImpl();
    }

    @Test
    void createCarBrand() {
        CarBrand carBrand = new CarBrand();
        carBrand.setBrandName("TestValue");
        Long id = CAR_BRAND_DAO.createCarBrand(carBrand);
        System.out.println(id);
    }

    @Test
    void getCarBrandById() {
        System.out.println("getCarBrandById");
        System.out.println(CAR_BRAND_DAO.getCarBrandById(31L).toString());
    }

    @Test
    void getCarBrandByBrandValue() {
        System.out.println("getCarBrandByBrandValue");
        System.out.println(CAR_BRAND_DAO.getCarBrandByBrandValue("TestValue"));
    }

    @Test
    void getAllCarBrand() {
        for (CarBrand carBrand : CAR_BRAND_DAO.getAllCarBrand()){
            System.out.println(carBrand.toString());
        }
    }

    @Test
    void deleteCarBrand() {
        CAR_BRAND_DAO.deleteCarBrand(31L);
    }
}