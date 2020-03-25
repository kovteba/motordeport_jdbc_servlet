package ua.nure.kovteba.finaltask.enumlist;

import java.util.ArrayList;
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
        List<CarStatus> listCarStatus = new ArrayList<>();
        listCarStatus.add(CarStatus.FREE);
        listCarStatus.add(CarStatus.BUSY);
        return listCarStatus;
    }

    public static CarStatus findCarStatus(String carClassValue){
        return getListCarStatus()
                .stream()
                .filter(s -> s.getCarStatusValue().equals(carClassValue))
                .findAny()
                .get();
    }

}
