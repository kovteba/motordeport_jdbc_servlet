package ua.nure.kovteba.finaltask.util.bot;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import ua.nure.kovteba.finaltask.controller.user.Admin;
import ua.nure.kovteba.finaltask.dao.car.CarDAO;
import ua.nure.kovteba.finaltask.dao.car.CarDAOImpl;
import ua.nure.kovteba.finaltask.dao.employmentstatus.EmploymentStatusDAO;
import ua.nure.kovteba.finaltask.dao.employmentstatus.EmploymentStatusDAOImpl;
import ua.nure.kovteba.finaltask.dao.flight.FlightDAO;
import ua.nure.kovteba.finaltask.dao.flight.FlightDAOImpl;
import ua.nure.kovteba.finaltask.dao.user.UserDAO;
import ua.nure.kovteba.finaltask.dao.user.UserDAOImpl;
import ua.nure.kovteba.finaltask.entity.Car;
import ua.nure.kovteba.finaltask.entity.EmploymentStatus;
import ua.nure.kovteba.finaltask.entity.Flight;
import ua.nure.kovteba.finaltask.entity.User;
import ua.nure.kovteba.finaltask.enumlist.CarStatus;
import ua.nure.kovteba.finaltask.enumlist.CarTechnicalStatus;
import ua.nure.kovteba.finaltask.enumlist.Employment;
import ua.nure.kovteba.finaltask.enumlist.FlightStatus;
import ua.nure.kovteba.finaltask.util.Encryption;

import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MotorDeportBot extends TelegramLongPollingBot {

   private static Logger log = Logger.getLogger(MotorDeportBot.class.getName());

   private static UserDAO userDAO;
   private static FlightDAO flightDAO;
   private static CarDAO carDAO;
   private static EmploymentStatusDAO employmentStatusDAO;

   public MotorDeportBot() {
      userDAO = new UserDAOImpl();
      flightDAO = new FlightDAOImpl();
      carDAO = new CarDAOImpl();
      employmentStatusDAO = new EmploymentStatusDAOImpl();
   }

//   public static void main(String[] args) {
//      userDAO.getAllUsers();
//      flightDAO.getAllFlight();
//   }

   @Override
   public void onUpdateReceived(Update update) {
      String message = update.getMessage().getText();
      String[] massageDone = null;

      if (message.length() > 3){
        massageDone = message.split("/");
         System.out.println(massageDone.length + "EEEEEEEEEEEEE");
         String phoneNumber = massageDone[0];
         String password = massageDone[1];
         String flightNumber = massageDone[2];
         String carStatus = massageDone[3];

         User user = userDAO.getUserByUserPhoneNumber(phoneNumber);
         if (user == null) {
            sendMsg(update.getMessage().getChatId().toString(), "Number not correct or user not exist");
            return;
         }

         if (Encryption.testOriginal(password, user.getPassword())){
            Flight flight = flightDAO.getFlightByNumber(flightNumber);
            System.out.println(flight.toString());
            if (flight.getId() == null){
               sendMsg(update.getMessage().getChatId().toString(), "Flight with number not exist");
               return;
            }
            Car carInFlight = flight.getCar();
            User driver = flight.getDriver();
            carDAO.changeCarStatus(carInFlight.getId(), CarStatus.FREE);
            CarTechnicalStatus carTechnicalStatus = null;
            try {
               carTechnicalStatus = CarTechnicalStatus.findIgnoreCase(carStatus);
            } catch (NoSuchElementException e){
               log.warning(e.getMessage());
               sendMsg(update.getMessage().getChatId().toString(), "Incorrect car technical status. Try again");
               return;
            }
            carDAO.changeCarTechnicalStatus(carInFlight.getId(),
                CarTechnicalStatus.findCarTechnicatlStatus(carTechnicalStatus.getCarTechnicalStatusValue()));
            flightDAO.changeFlightStatus(flight.getId(), FlightStatus.DONE);
            employmentStatusDAO.changeEmploymentStatus(driver.getId(), Employment.FREE);
            sendMsg(update.getMessage().getChatId().toString(), "Flight DONE");
         }

      } else {
         sendMsg(update.getMessage().getChatId().toString(), "Enter value by format phoneNumber/password/flightNumber/carStatus");
         return;
      }

   }

   public synchronized void sendMsg(String chatId, String s) {
      SendMessage sendMessage = new SendMessage();
      sendMessage.enableMarkdown(true);
      sendMessage.setChatId(chatId);
      sendMessage.setText(s);
      try {
         sendMessage(sendMessage);
      } catch (TelegramApiException e) {
         log.info(e.toString());
      }
   }

   @Override
   public String getBotUsername() {
      return "motorDeportBot";
   }

   @Override
   public String getBotToken() {
      return "1017849293:AAGI-dCgHmS7r9noHUC1rnHOUojkuQFLULE";
   }


}
