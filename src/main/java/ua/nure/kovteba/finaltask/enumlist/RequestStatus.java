package ua.nure.kovteba.finaltask.enumlist;

public enum  RequestStatus {

    OPEN("OPEN"),

    CLOSED("CLOSED");

    private final String requestSatus;

    RequestStatus(String requestSatus) {
        this.requestSatus = requestSatus;
    }

    public String getRequestSatus() {
        return requestSatus;
    }

}
