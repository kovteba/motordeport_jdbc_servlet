package ua.nure.kovteba.finaltask.entity;

import ua.nure.kovteba.finaltask.enumlist.FlightStatus;
import java.time.ZonedDateTime;
import java.util.Objects;

public class Flight {

    private Long id;

    private String flightNumber;

    private User driver;

    private Car car;

    private FlightStatus flightStatus;

    private ZonedDateTime startDate;

    private ZonedDateTime endDate;

    private Long request;

    public boolean testEndDate(){
        return endDate.toLocalDate().isAfter(ZonedDateTime.now().toLocalDate());
    }

    public Flight() {
    }

    public Flight(Long id,
                  String flightNumber,
                  User driver,
                  Car car,
                  FlightStatus flightStatus,
                  ZonedDateTime startDate,
                  ZonedDateTime endDate,
                  Long request) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.driver = driver;
        this.car = car;
        this.flightStatus = flightStatus;
        this.startDate = startDate;
        this.endDate = endDate;
        this.request = request;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public User getDriver() {
        return driver;
    }

    public void setDriver(User driver) {
        this.driver = driver;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public FlightStatus getFlightStatus() {
        return flightStatus;
    }

    public void setFlightStatus(FlightStatus flightStatus) {
        this.flightStatus = flightStatus;
    }

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(ZonedDateTime endDate) {
        this.endDate = endDate;
    }

    public Long getRequest() {
        return request;
    }

    public void setRequest(Long request) {
        this.request = request;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return Objects.equals(id, flight.id) &&
                Objects.equals(flightNumber, flight.flightNumber) &&
                Objects.equals(driver, flight.driver) &&
                Objects.equals(car, flight.car) &&
                flightStatus == flight.flightStatus &&
                Objects.equals(startDate, flight.startDate) &&
                Objects.equals(endDate, flight.endDate) &&
                Objects.equals(request, flight.request);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,
                flightNumber,
                driver,
                car,
                flightStatus,
                startDate,
                endDate,
                request);
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", flightNumber='" + flightNumber + '\'' +
                ", driver=" + driver +
                ", car=" + car +
                ", flightStatus=" + flightStatus +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", request=" + request +
                '}';
    }
}
