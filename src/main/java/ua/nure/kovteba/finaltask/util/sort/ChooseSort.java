package ua.nure.kovteba.finaltask.util.sort;

import ua.nure.kovteba.finaltask.entity.Flight;
import ua.nure.kovteba.finaltask.enumlist.FlightStatus;

import java.util.List;

public class ChooseSort {

    public static List<Flight> chooseTypeSort(String type, List<Flight> flights){
        switch (type) {
            case "OPEN": {
                flights.sort(new CompareFlightByStatus(FlightStatus.OPEN));
                break;
            }
            case "IN PROGRESS": {
                flights.sort(new CompareFlightByStatus(FlightStatus.INPROGRESS));
                break;
            }
            case "CLOSE": {
                flights.sort(new CompareFlightByStatus(FlightStatus.CLOSE));
                break;
            }
            case "CANCELED": {
                flights.sort(new CompareFlightByStatus(FlightStatus.CANСELED));
                break;
            }
            case "DONE": {
                flights.sort(new CompareFlightByStatus(FlightStatus.DONE));
                break;
            }
            case "endDateDown": {
                flights.sort(new CompareFlightByEndDateDown());
                break;
            }
            case "endDateUp": {
                flights.sort(new CompareFlightByEndDateUp());
                break;
            }
            case "flightNumberDown": {
                flights.sort(new CompareFlightByNumberDown());
                break;
            }
            case "flightNumberUp": {
                flights.sort(new CompareFlightByNumberUp());
                break;
            }
            case "startDateDown": {
                flights.sort(new CompareFlightByStartDateDown());
                break;
            }
            case "startDateUp": {
                flights.sort(new CompareFlightByStartDateUp());
                break;
            }
            default:
                break;
        }
        return flights;
    }

}
