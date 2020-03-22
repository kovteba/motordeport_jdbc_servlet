package ua.nure.kovteba.finaltask.dao.car;

import ua.nure.kovteba.finaltask.entity.Car;
import ua.nure.kovteba.finaltask.enumlist.CarStatus;
import ua.nure.kovteba.finaltask.enumlist.CarTechnicalStatus;

public interface CarDAO {

    Long createCar(Car car);

    Car getCarById(Long id);

    void changeCarTechnicalStatus(Long id, CarTechnicalStatus carTechnicalStatus);

    void changeCarStatus(Long id, CarStatus carStatus);

}
