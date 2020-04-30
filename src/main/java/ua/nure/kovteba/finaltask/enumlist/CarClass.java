package ua.nure.kovteba.finaltask.enumlist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum  CarClass {

    ECONOMY("ECONOMY"),

    STANDART("STANDART"),

    COMFORT("COMFORT"),

    BUSINESS("BUSINESS"),

    STATIONWAGON("STATION WAGON"),

    MINIVAN("MINIVAN");

    private final String classValue;

    CarClass(String roleValue) {
        this.classValue = roleValue;
    }

    public String getClassValue() {
        return classValue;
    }

    public static List<CarClass> getListCarClass(){
        return Arrays.asList(CarClass.values());
    }

    public static CarClass findCarClass(String carClassValue){
        return getListCarClass()
                .stream()
                .filter(s -> s.getClassValue().equals(carClassValue))
                .findAny()
                .get();
    }

}
