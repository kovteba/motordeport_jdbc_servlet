package ua.nure.kovteba.finaltask.enumlist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum FlightStatus {

    OPEN("OPEN"),

    INPROGRESS("IN PROGRESS"),

    CLOSE("CLOSE"),

    CANСELED("CANCELED"),

    DONE("DONE");

    private final String statusValue;

    FlightStatus(String statusValue) {
        this.statusValue = statusValue;
    }

    public String getStatusValue() {
        return statusValue;
    }

    public static List<FlightStatus> getFlightStatusList(){
        return Arrays.asList(FlightStatus.values());
    }

    public static FlightStatus findFlightStatus(String flightStatus){
        return getFlightStatusList()
                .stream()
                .filter(s -> s.getStatusValue().equals(flightStatus))
                .findAny()
                .get();
    }

}
