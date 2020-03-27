package ua.nure.kovteba.finaltask.enumlist;

import java.util.ArrayList;
import java.util.List;

public enum Employment {

    BUSY("BUSY"),

    FREE("FREE");

    private final String status;

    Employment(String status) {
        this.status = status;
    }

    public String getEmploymentStatusValue() {
        return status;
    }

    public static List<CarStatus> getListEmploymentStatus(){
        List<CarStatus> listCarStatus = new ArrayList<>();
        listCarStatus.add(CarStatus.FREE);
        listCarStatus.add(CarStatus.BUSY);
        return listCarStatus;
    }

    public static CarStatus findCarStatus(String carClassValue){
        return getListEmploymentStatus()
                .stream()
                .filter(s -> s.getCarStatusValue().equals(carClassValue))
                .findAny()
                .get();
    }

}
