package ua.nure.kovteba.finaltask.enumlist;

import java.util.ArrayList;
import java.util.Arrays;
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

    public static List<Employment> getListEmploymentStatus(){
        return Arrays.asList(Employment.values());
    }

    public static Employment findCarStatus(String employmentValue){
        return getListEmploymentStatus()
                .stream()
                .filter(s -> s.getListEmploymentStatus().equals(employmentValue))
                .findAny()
                .get();
    }

}
