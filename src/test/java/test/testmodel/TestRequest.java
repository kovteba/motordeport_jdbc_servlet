package test.testmodel;




import ua.nure.kovteba.finaltask.entity.Request;
import ua.nure.kovteba.finaltask.entity.User;
import ua.nure.kovteba.finaltask.enumlist.CarClass;
import ua.nure.kovteba.finaltask.enumlist.Role;

import java.time.ZonedDateTime;

public class TestRequest {

    public static void main(String[] args) {

        User admin = new User();
        admin.setId(1L);
        admin.setFirstName("Dima");
        admin.setLastName("Kovteba");
        admin.setPhoneNumber("0509769376");
        admin.setRole(Role.ADMIN.getRoleValue());

        User driver = new User();
        driver.setId(1L);
        driver.setFirstName("Dima");
        driver.setLastName("Kovteba");
        driver.setPhoneNumber("0509769376");
        driver.setRole(Role.DRIVER.getRoleValue());

        Request request1 = new Request();
        request1.setId(1L);
        request1.setDriver(driver);
        request1.setCarClass(CarClass.COMFORT);
        request1.setLoadCapacity(1500);
        request1.setSeats(20);
        request1.setLuggageCompartment(true);
        request1.setAirConditioning(true);
        request1.setNavigator(true);

        System.out.println(request1.toString());


        System.out.println(ZonedDateTime.now());

    }

}
