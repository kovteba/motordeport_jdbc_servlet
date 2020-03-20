package ua.nure.kovteba.finaltask.enumlist;

public enum CarStatus {

    GOOD("good"),

    REPAIRS("repairs"),

    BUSY("busy"),

    FREE("free");

    private final String carStatus;

    CarStatus(String carStatus) {
        this.carStatus = carStatus;
    }

    public String getCarStatus() {
        return carStatus;
    }

}
