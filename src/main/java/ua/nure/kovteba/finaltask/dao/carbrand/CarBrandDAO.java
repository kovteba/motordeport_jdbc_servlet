package ua.nure.kovteba.finaltask.dao.carbrand;

import ua.nure.kovteba.finaltask.entity.CarBrand;

public interface CarBrandDAO {

    Long cteateCarBrand(CarBrand carBrand);

    CarBrand getCarBrandById(Long id);

    CarBrand getCarBrandByBrandValue(String brandValue);

}
