package ua.nure.kovteba.finaltask.util;

import ua.nure.kovteba.finaltask.dao.flight.FlightDAO;
import ua.nure.kovteba.finaltask.dao.flight.FlightDAOImpl;
import ua.nure.kovteba.finaltask.entity.Flight;
import ua.nure.kovteba.finaltask.enumlist.FlightStatus;

import java.time.ZonedDateTime;
import java.util.List;

public class TestFlightByDate extends Thread {

   private static FlightDAO flightDAO;

   public TestFlightByDate() {
      flightDAO = new FlightDAOImpl();
   }

   @Override
   public void run() {
      while (true){
         List<Flight> flights = flightDAO.getAllFlight();
         for (Flight flight : flights){
            if (ZonedDateTime.now().toLocalDate().isBefore(flight.getStartDate().toLocalDate())){
               flightDAO.changeFlightStatus(flight.getId(), FlightStatus.INPROGRESS);
            }
         }
         try {
            Thread.sleep(3600000);
//            Thread.sleep(500);
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
   }

}
