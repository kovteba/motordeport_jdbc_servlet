package ua.nure.kovteba.finaltask.dao.carbrand;

import ua.nure.kovteba.finaltask.entity.CarBrand;

import java.util.List;

public interface CarBrandDAO {

    Long createCarBrand(CarBrand carBrand);

    CarBrand getCarBrandById(Long id);

    CarBrand getCarBrandByBrandValue(String brandValue);

    List<CarBrand> getAllCarBrand();

    void deleteCarBrand(Long id);

}
