package ua.nure.kovteba.finaltask.entity;

import ua.nure.kovteba.finaltask.enumlist.CarClass;
import ua.nure.kovteba.finaltask.enumlist.CarStatus;
import ua.nure.kovteba.finaltask.enumlist.CarTechnicalStatus;

import java.io.Serializable;
import java.util.Objects;

public class Car implements Serializable {

    private Long id;

    private CarBrand carBrand;

    private CarClass carClass;

    private String carNumber;

    private int loadCapacity;

    private int seats;

    private Boolean luggageCompartment;

    private Boolean airConditioning;

    private Boolean navigator;

    private CarTechnicalStatus carTechnicalStatus;

    private CarStatus carStatus;

    public Car() {
    }

    public Car(Long id,
               CarBrand carBrand,
               CarClass carClass,
               String carNumber,
               int loadCapacity,
               int seats,
               Boolean luggageCompartment,
               Boolean airConditioning,
               Boolean navigator,
               CarTechnicalStatus carTechnicalStatus,
               CarStatus carStatus) {
        this.id = id;
        this.carBrand = carBrand;
        this.carClass = carClass;
        this.carNumber = carNumber;
        this.loadCapacity = loadCapacity;
        this.seats = seats;
        this.luggageCompartment = luggageCompartment;
        this.airConditioning = airConditioning;
        this.navigator = navigator;
        this.carTechnicalStatus = carTechnicalStatus;
        this.carStatus = carStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CarBrand getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(CarBrand carBrand) {
        this.carBrand = carBrand;
    }

    public CarClass getCarClass() {
        return carClass;
    }

    public void setCarClass(CarClass carClass) {
        this.carClass = carClass;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public int getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(int loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public Boolean getLuggageCompartment() {
        return luggageCompartment;
    }

    public void setLuggageCompartment(Boolean luggageCompartment) {
        this.luggageCompartment = luggageCompartment;
    }

    public Boolean getAirConditioning() {
        return airConditioning;
    }

    public void setAirConditioning(Boolean airConditioning) {
        this.airConditioning = airConditioning;
    }

    public Boolean getNavigator() {
        return navigator;
    }

    public void setNavigator(Boolean navigator) {
        this.navigator = navigator;
    }

    public CarTechnicalStatus getCarTechnicalStatus() {
        return carTechnicalStatus;
    }

    public void setCarTechnicalStatus(CarTechnicalStatus carTechnicalStatus) {
        this.carTechnicalStatus = carTechnicalStatus;
    }

    public CarStatus getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(CarStatus carStatus) {
        this.carStatus = carStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return loadCapacity == car.loadCapacity &&
                seats == car.seats &&
                Objects.equals(id, car.id) &&
                Objects.equals(carBrand, car.carBrand) &&
                carClass == car.carClass &&
                Objects.equals(carNumber, car.carNumber) &&
                Objects.equals(luggageCompartment, car.luggageCompartment) &&
                Objects.equals(airConditioning, car.airConditioning) &&
                Objects.equals(navigator, car.navigator) &&
                carTechnicalStatus == car.carTechnicalStatus &&
                carStatus == car.carStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,
                carBrand,
                carClass,
                carNumber,
                loadCapacity,
                seats,
                luggageCompartment,
                airConditioning,
                navigator,
                carTechnicalStatus,
                carStatus);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", carBrand=" + carBrand +
                ", carClass=" + carClass +
                ", carNumber='" + carNumber + '\'' +
                ", loadCapacity=" + loadCapacity +
                ", seats=" + seats +
                ", luggageCompartment=" + luggageCompartment +
                ", airConditioning=" + airConditioning +
                ", navigator=" + navigator +
                ", carTechnicalStatus=" + carTechnicalStatus +
                ", carStatus=" + carStatus +
                '}';
    }
}
