package ua.nure.kovteba.finaltask.enumlist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public static List<RequestStatus> getListRequestStatus(){
        return Arrays.asList(RequestStatus.values());
    }

    public static RequestStatus findRequestStatus(String requestStatus){
        return getListRequestStatus()
                .stream()
                .filter(status -> status.getRequestSatus().equals(requestStatus))
                .findAny()
                .get();
    }

}
