package ua.nure.kovteba.finaltask.entity;

import lombok.*;
import ua.nure.kovteba.finaltask.enumlist.CarClass;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Car {

    private Long id;

    private CarBrand carBrand;

    private CarClass carClass;

    private String carNumber;

    private int loadCapacity;

    private int seats;

    private Boolean luggageCompartment;

    private Boolean airConditioning;

    private Boolean navigator;

}
