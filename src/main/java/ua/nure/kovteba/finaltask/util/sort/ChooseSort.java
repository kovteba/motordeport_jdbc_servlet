package ua.nure.kovteba.finaltask.util.sort;

import ua.nure.kovteba.finaltask.controller.flight.CreateFlight;
import ua.nure.kovteba.finaltask.entity.Flight;
import ua.nure.kovteba.finaltask.enumlist.FlightStatus;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public class ChooseSort {

    //Create logger
    private static Logger log = Logger.getLogger(ChooseSort.class.getName());

    public static List<Flight> chooseTypeSort(String type, List<Flight> flights) {
        switch (type) {
            case "OPEN": {
                log.info("Sort flight by status 'OPEN'");
                flights.sort((o1, o2) -> {
                    if (o2.getFlightStatus().getStatusValue().equals(FlightStatus.OPEN.getStatusValue())) {
                        return 1;
                    } else {
                        return -1;
                    }
                });
                break;
            }
            case "IN PROGRESS": {
                log.info("Sort flight by status 'INPROGRESS'");
                flights.sort((o1, o2) -> {
                    if (o2.getFlightStatus().getStatusValue().equals(FlightStatus.INPROGRESS.getStatusValue())) {
                        return 1;
                    } else {
                        return -1;
                    }
                });
                break;
            }
            case "CLOSE": {
                log.info("Sort flight by status 'CLOSE'");
                flights.sort((o1, o2) -> {
                    if (o2.getFlightStatus().getStatusValue().equals(FlightStatus.CLOSE.getStatusValue())) {
                        return 1;
                    } else {
                        return -1;
                    }
                });
                break;
            }
            case "CANCELED": {
                log.info("Sort flight by status 'CANСELED'");
                flights.sort((o1, o2) -> {
                    if (o2.getFlightStatus().getStatusValue().equals(FlightStatus.CANСELED.getStatusValue())) {
                        return 1;
                    } else {
                        return -1;
                    }
                });
                break;
            }
            case "DONE": {
                log.info("Sort flight by status 'DONE'");
                flights.sort((o1, o2) -> {
                    if (o2.getFlightStatus().getStatusValue().equals(FlightStatus.DONE.getStatusValue())) {
                        return 1;
                    } else {
                        return -1;
                    }
                });
                break;
            }
            case "endDateDown": {
                log.info("Sort flight by end date 'DOWN'");
                flights.sort((o1, o2) -> {
                    if (o2.getEndDate().toLocalDate().equals(o1.getEndDate().toLocalDate())) {
                        return 0;
                    } else if (o2.getEndDate().toLocalDate().isAfter(o1.getEndDate().toLocalDate())) {
                        return 1;
                    } else {
                        return -1;
                    }
                });
                break;
            }
            case "endDateUp": {
                log.info("Sort flight by end date 'UP'");
                flights.sort((o1, o2) -> {
                    if (o2.getEndDate().toLocalDate().equals(o1.getEndDate().toLocalDate())) {
                        return 0;
                    } else if (o2.getEndDate().toLocalDate().isAfter(o1.getEndDate().toLocalDate())) {
                        return -1;
                    } else {
                        return 1;
                    }
                });
                break;
            }
            case "flightNumberDown": {
                log.info("Sort flight by number 'DOWN'");
                flights.sort((o1, o2) -> {
                    if (Integer.parseInt(o2.getFlightNumber()) == Integer.parseInt(o1.getFlightNumber())){
                        return 0;
                    } else if (Integer.parseInt(o2.getFlightNumber()) > Integer.parseInt(o1.getFlightNumber())){
                        return 1;
                    } else {
                        return -1;
                    }
                });
                break;
            }
            case "flightNumberUp": {
                log.info("Sort flight by number 'UP'");
                flights.sort((o1, o2) -> {
                    if (Integer.parseInt(o2.getFlightNumber()) == Integer.parseInt(o1.getFlightNumber())){
                        return 0;
                    } else if (Integer.parseInt(o2.getFlightNumber()) > Integer.parseInt(o1.getFlightNumber())){
                        return -1;
                    } else {
                        return 1;
                    }
                });
                break;
            }
            case "startDateDown": {
                log.info("Sort flight by start date 'DOWN'");
                flights.sort((o1, o2) -> {
                    if (o2.getStartDate().toLocalDate().equals(o1.getStartDate().toLocalDate())) {
                        return 0;
                    } else if (o2.getStartDate().toLocalDate().isAfter(o1.getStartDate().toLocalDate())) {
                        return 1;
                    } else {
                        return -1;
                    }
                });
                break;
            }
            case "startDateUp": {
                log.info("Sort flight by start date 'UP'");
                flights.sort((o1, o2) -> {
                    if (o2.getStartDate().toLocalDate().equals(o1.getStartDate().toLocalDate())) {
                        return 0;
                    } else if (o2.getStartDate().toLocalDate().isAfter(o1.getStartDate().toLocalDate())) {
                        return -1;
                    } else {
                        return 1;
                    }
                });
                break;
            }
            default:
                log.info("Тo matches found");
                break;
        }
        return flights;
    }

}
