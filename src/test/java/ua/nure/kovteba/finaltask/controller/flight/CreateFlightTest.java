package ua.nure.kovteba.finaltask.controller.flight;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CreateFlightTest {

    @Test
    void init() {
    }

    @Test
    void doPost() {




        String startDate = "2020-12-31";
        String startTime = "12:59";

        System.out.println(ZonedDateTime.now().toString());


        ZonedDateTime zonedDateTime = ZonedDateTime.of(LocalDate.parse(startDate), LocalTime.parse(startTime), ZonedDateTime.now().getZone());

        System.out.println(zonedDateTime);
//        System.out.println(ZonedDateTime.s);
//        System.out.println(ZonedDateTime.now());
    }
}