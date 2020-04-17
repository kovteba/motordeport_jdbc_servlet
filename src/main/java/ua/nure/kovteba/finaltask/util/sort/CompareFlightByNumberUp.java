package ua.nure.kovteba.finaltask.util.sort;

import ua.nure.kovteba.finaltask.entity.Flight;

import java.util.Comparator;

public class CompareFlightByNumberUp  implements Comparator<Flight> {

    @Override
    public int compare(Flight o1, Flight o2) {
        if (Integer.parseInt(o2.getFlightNumber()) == Integer.parseInt(o1.getFlightNumber())){
            return 0;
        } else if (Integer.parseInt(o2.getFlightNumber()) > Integer.parseInt(o1.getFlightNumber())){
            return -1;
        } else {
            return 1;
        }
    }

}
