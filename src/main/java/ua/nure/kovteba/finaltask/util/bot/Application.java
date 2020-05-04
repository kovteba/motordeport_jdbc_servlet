package ua.nure.kovteba.finaltask.util.bot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

public class Application {
   public static void main() {
      ApiContextInitializer.init();
      TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
      try {
         telegramBotsApi.registerBot(new MotorDeportBot());

      } catch (TelegramApiRequestException e) {
         e.printStackTrace();
      }
   }
}
