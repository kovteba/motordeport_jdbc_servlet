package ua.nure.kovteba.finaltask.entity;

import lombok.*;
import ua.nure.kovteba.finaltask.enumlist.Role;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class User implements Serializable {

    private static final long serialVersionUID = -913891286194123998L;

    private Long id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private Role role;

    private String password;

    private String email;

}
