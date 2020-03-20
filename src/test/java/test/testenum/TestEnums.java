package test.testenum;


import ua.nure.kovteba.finaltask.enumlist.CarClass;
import ua.nure.kovteba.finaltask.enumlist.CarStatus;
import ua.nure.kovteba.finaltask.enumlist.FlightStatus;
import ua.nure.kovteba.finaltask.enumlist.Role;

public class TestEnums {

    public static void main(String[] args) {

        System.out.println("LIST ROLE");
        System.out.println(Role.ADMIN.getRoleValue());
        System.out.println(Role.DISPATCHER.getRoleValue());
        System.out.println(Role.DRIVER.getRoleValue() + "\n");

        System.out.println("LIST FLIGHT STATUS");
        System.out.println(FlightStatus.OPEN.getStatusValue());
        System.out.println(FlightStatus.INPROGRESS.getStatusValue());
        System.out.println(FlightStatus.CLOSE.getStatusValue());
        System.out.println(FlightStatus.CANСELED.getStatusValue() + "\n");

        System.out.println("LIST CAR CLASS");
        System.out.println(CarClass.ECONOMY.getClassValue());
        System.out.println(CarClass.STANDART.getClassValue());
        System.out.println(CarClass.COMFORT.getClassValue());
        System.out.println(CarClass.BUSINESS.getClassValue());
        System.out.println(CarClass.STATIONWAGON.getClassValue());
        System.out.println(CarClass.MINIVAN.getClassValue() + "\n");

        System.out.println("LIST CAR STATUS");
        System.out.println(CarStatus.GOOD.getCarStatus());
        System.out.println(CarStatus.REPAIRS.getCarStatus());
        System.out.println(CarStatus.FREE.getCarStatus());
        System.out.println(CarStatus.BUSY.getCarStatus());


    }

}
