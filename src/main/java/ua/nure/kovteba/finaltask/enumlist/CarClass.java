package ua.nure.kovteba.finaltask.enumlist;

import java.util.ArrayList;
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
        List<CarClass> classList = new ArrayList<>();
        classList.add(CarClass.ECONOMY);
        classList.add(CarClass.STANDART);
        classList.add(CarClass.COMFORT);
        classList.add(CarClass.BUSINESS);
        classList.add(CarClass.STATIONWAGON);
        classList.add(CarClass.MINIVAN);
        return classList;
    }

    public static CarClass findCarClass(String carClassValue){
        return getListCarClass()
                .stream()
                .filter(s -> s.getClassValue().equals(carClassValue))
                .findAny()
                .get();
    }


}
