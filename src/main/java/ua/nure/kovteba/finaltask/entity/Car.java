package ua.nure.kovteba.finaltask.entity;

import lombok.*;
import ua.nure.kovteba.finaltask.enumlist.CarClass;
import ua.nure.kovteba.finaltask.enumlist.CarStatus;
import ua.nure.kovteba.finaltask.enumlist.CarTechnicalStatus;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Car implements Serializable {

    private Long id;

    private CarBrand carBrand;

    private CarClass carClass;

    private String carNumber;

    private int loadCapacity;

    private int seats;

    private Boolean luggageCompartment;

    private Boolean airConditioning;

    private Boolean navigator;

    private CarTechnicalStatus carTechnicalStatus;

    private CarStatus carStatus;

}
