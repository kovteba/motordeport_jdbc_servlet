package ua.nure.kovteba.finaltask.entity;

import lombok.*;
import ua.nure.kovteba.finaltask.enumlist.FlightStatus;
import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Flight {

    private Long id;

    private String flightNumber;

    private User driver;

    private Car car;

    private FlightStatus flightStatus;

    private ZonedDateTime startDate;

    private ZonedDateTime endDate;

    private Long request;

}
