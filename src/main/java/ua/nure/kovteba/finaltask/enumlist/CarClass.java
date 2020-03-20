package ua.nure.kovteba.finaltask.enumlist;

import java.util.ArrayList;
import java.util.List;

public enum  CarClass {

    ECONOMY("Economy"),

    STANDART("Standard"),

    COMFORT("Comfort"),

    BUSINESS("Business"),

    STATIONWAGON("Station Wagon"),

    MINIVAN("Minivan");

    private final String classValue;

    CarClass(String roleValue) {
        this.classValue = roleValue;
    }

    public String getClassValue() {
        return classValue;
    }

    public static List<String> getListCarClass(){
        List<String> classList = new ArrayList<>();
        classList.add(CarClass.ECONOMY.getClassValue());
        classList.add(CarClass.STANDART.getClassValue());
        classList.add(CarClass.COMFORT.getClassValue());
        classList.add(CarClass.BUSINESS.getClassValue());
        classList.add(CarClass.STATIONWAGON.getClassValue());
        classList.add(CarClass.MINIVAN.getClassValue());
        return classList;
    }


}
