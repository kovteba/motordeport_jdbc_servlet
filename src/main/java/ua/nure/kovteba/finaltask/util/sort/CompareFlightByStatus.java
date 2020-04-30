//package ua.nure.kovteba.finaltask.util.sort;
//
//import ua.nure.kovteba.finaltask.entity.Flight;
//import ua.nure.kovteba.finaltask.enumlist.FlightStatus;
//
//import java.util.Comparator;
//
//public class CompareFlightByStatus  implements Comparator<Flight> {
//
//    private FlightStatus status;
//
//    public CompareFlightByStatus(FlightStatus status) {
//        this.status = status;
//    }
//
//    @Override
//    public int compare(Flight o1, Flight o2) {
//        if (o2.getFlightStatus().getStatusValue().equals(status.getStatusValue())) {
//            return 1;
//        } else {
//            return -1;
//        }
//    }
//
//}
