package ua.nure.kovteba.finaltask.enumlist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum CarTechnicalStatus {

    GOOD("GOOD"),

    REPAIRS("REPAIRS");

    private final String carTechnicalStatus;

    CarTechnicalStatus(String carTechnicalStatus) {
        this.carTechnicalStatus = carTechnicalStatus;
    }

    public String getCarTechnicalStatusValue() {
        return carTechnicalStatus;
    }

    public static List<CarTechnicalStatus> getListCarTechnicalStatus(){
        return Arrays.asList(CarTechnicalStatus.values());
    }

    public static CarTechnicalStatus findCarTechnicatlStatus(String carClassValue){
        return getListCarTechnicalStatus()
                .stream()
                .filter(s -> s.getCarTechnicalStatusValue().equals(carClassValue))
                .findAny()
                .get();
    }

}
