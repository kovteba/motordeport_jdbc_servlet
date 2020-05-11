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
      System.out.println("TEST RUN");
      while (true){
         System.out.println("TEST WHILE");
         List<Flight> flights = flightDAO.getAllFlight();
         for (Flight flight : flights){
            System.out.println("Now : " + ZonedDateTime.now().toLocalDate());
            System.out.println("flight : " + flight.getStartDate().toLocalDate());
            if (ZonedDateTime.now().toLocalDate().isAfter(flight.getStartDate().toLocalDate())){
               if (ZonedDateTime.now().toLocalDate().isBefore(flight.getEndDate().toLocalDate())){
                  flightDAO.changeFlightStatus(flight.getId(), FlightStatus.INPROGRESS);
                  System.out.println("TEST");
               }
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
