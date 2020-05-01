package ua.nure.kovteba.finaltask.entity;

import ua.nure.kovteba.finaltask.enumlist.CarClass;
import ua.nure.kovteba.finaltask.enumlist.RequestStatus;

import java.io.Serializable;
import java.util.Objects;

public class Request implements Serializable {

    private Long id;

    private User driver;

    private CarClass carClass;

    private int loadCapacity;

    private int seats;

    private Boolean luggageCompartment;

    private Boolean airConditioning;

    private Boolean navigator;

    private RequestStatus requestStatus;

    public Request() {
    }

    public Request(Long id,
                   User driver,
                   CarClass carClass,
                   int loadCapacity,
                   int seats,
                   Boolean luggageCompartment,
                   Boolean airConditioning,
                   Boolean navigator,
                   RequestStatus requestStatus) {
        this.id = id;
        this.driver = driver;
        this.carClass = carClass;
        this.loadCapacity = loadCapacity;
        this.seats = seats;
        this.luggageCompartment = luggageCompartment;
        this.airConditioning = airConditioning;
        this.navigator = navigator;
        this.requestStatus = requestStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getDriver() {
        return driver;
    }

    public void setDriver(User driver) {
        this.driver = driver;
    }

    public CarClass getCarClass() {
        return carClass;
    }

    public void setCarClass(CarClass carClass) {
        this.carClass = carClass;
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

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return loadCapacity == request.loadCapacity &&
                seats == request.seats &&
                Objects.equals(id, request.id) &&
                Objects.equals(driver, request.driver) &&
                carClass == request.carClass &&
                Objects.equals(luggageCompartment, request.luggageCompartment) &&
                Objects.equals(airConditioning, request.airConditioning) &&
                Objects.equals(navigator, request.navigator) &&
                requestStatus == request.requestStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,
                driver,
                carClass,
                loadCapacity,
                seats,
                luggageCompartment,
                airConditioning,
                navigator,
                requestStatus);
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", driver=" + driver +
                ", carClass=" + carClass +
                ", loadCapacity=" + loadCapacity +
                ", seats=" + seats +
                ", luggageCompartment=" + luggageCompartment +
                ", airConditioning=" + airConditioning +
                ", navigator=" + navigator +
                ", requestStatus=" + requestStatus +
                '}';
    }
}
