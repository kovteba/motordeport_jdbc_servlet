package ua.nure.kovteba.finaltask.util.sort;

import ua.nure.kovteba.finaltask.entity.Flight;

import java.util.Comparator;

public class CompareFlightByEndDateDown implements Comparator<Flight> {

    @Override
    public int compare(Flight o1, Flight o2) {
        if (o2.getEndDate().toLocalDate().equals(o1.getEndDate().toLocalDate())) {
            return 0;
        } else if (o2.getEndDate().toLocalDate().isAfter(o1.getEndDate().toLocalDate())) {
            return 1;
        } else {
            return -1;
        }
    }

}
