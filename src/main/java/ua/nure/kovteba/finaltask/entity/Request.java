package ua.nure.kovteba.finaltask.entity;

import lombok.*;
import ua.nure.kovteba.finaltask.enumlist.CarClass;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Request {

    private Long id;

    private User driver;

    private CarClass carClass;

    private int loadCapacity;

    private int seats;

    private Boolean luggageCompartment;

    private Boolean airConditioning;

    private Boolean navigator;

}