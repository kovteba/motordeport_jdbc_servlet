package ua.nure.kovteba.finaltask.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class EmploymentStatus {

    private Long id;

    private Long idDriver;

    private String value;

}
