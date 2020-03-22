package ua.nure.kovteba.finaltask.entity;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CarBrand implements Serializable {

    private Long id;

    private String brandName;

}
