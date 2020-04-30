package ua.nure.kovteba.finaltask.enumlist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum  CarStatus {

    BUSY("BUSY"),

    FREE("FREE");

    private final String carStatus;

    CarStatus(String carStatus) {
        this.carStatus = carStatus;
    }

    public String getCarStatusValue() {
        return carStatus;
    }

    public static List<CarStatus> getListCarStatus(){
        return Arrays.asList(CarStatus.values());
    }

    public static CarStatus findCarStatus(String carClassValue){
        return getListCarStatus()
                .stream()
                .filter(s -> s.getCarStatusValue().equals(carClassValue))
                .findAny()
                .get();
    }

}
