package ua.nure.kovteba.finaltask.enumlist;

public enum FlightStatus {

    OPEN("open"),

    INPROGRESS("inProgress"),

    CLOSE("close"),

    CANСELED("canceled");

    private final String statusValue;

    FlightStatus(String statusValue) {
        this.statusValue = statusValue;
    }

    public String getStatusValue() {
        return statusValue;
    }

}
