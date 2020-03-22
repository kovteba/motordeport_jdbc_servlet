package ua.nure.kovteba.finaltask.enumlist;

import java.util.ArrayList;
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
        List<RequestStatus> requestStatusList = new ArrayList<>();
        requestStatusList.add(RequestStatus.OPEN);
        requestStatusList.add(RequestStatus.CLOSED);
        return requestStatusList;
    }

    public static RequestStatus findRequestStatus(String requestStatus){
        return getListRequestStatus()
                .stream()
                .filter(status -> status.getRequestSatus().equals(requestStatus))
                .findAny()
                .get();
    }

}
