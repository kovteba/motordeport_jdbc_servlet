package ua.nure.kovteba.finaltask.entity;

import lombok.*;
import ua.nure.kovteba.finaltask.enumlist.Role;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class User {

    private Long id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String role;

    private String password;

}
