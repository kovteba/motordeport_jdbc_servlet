package ua.nure.kovteba.finaltask.util.sort;

import ua.nure.kovteba.finaltask.entity.Flight;

import java.util.Comparator;

public class CompareFlightByStartDateDown implements Comparator<Flight> {

    @Override
    public int compare(Flight o1, Flight o2) {
        if (o2.getStartDate().toLocalDate().equals(o1.getStartDate().toLocalDate())) {
            return 0;
        } else if (o2.getStartDate().toLocalDate().isAfter(o1.getStartDate().toLocalDate())) {
            return 1;
        } else {
            return -1;
        }
    }

}
