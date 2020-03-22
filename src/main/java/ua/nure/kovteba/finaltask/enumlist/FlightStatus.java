package ua.nure.kovteba.finaltask.enumlist;

import java.util.ArrayList;
import java.util.List;

public enum FlightStatus {

    OPEN("OPEN"),

    INPROGRESS("IN PROGRESS"),

    CLOSE("CLOSE"),

    CANСELED("CANCELED");

    private final String statusValue;

    FlightStatus(String statusValue) {
        this.statusValue = statusValue;
    }

    public String getStatusValue() {
        return statusValue;
    }

    public static List<FlightStatus> getFlightStatus(){
        List<FlightStatus> listCarStatus = new ArrayList<>();
        listCarStatus.add(FlightStatus.OPEN);
        listCarStatus.add(FlightStatus.INPROGRESS);
        listCarStatus.add(FlightStatus.CLOSE);
        listCarStatus.add(FlightStatus.CANСELED);
        return listCarStatus;
    }

    public static FlightStatus findFlightStatus(String flightStatus){
        return getFlightStatus()
                .stream()
                .filter(s -> s.getStatusValue().equals(flightStatus))
                .findAny()
                .get();
    }

}
