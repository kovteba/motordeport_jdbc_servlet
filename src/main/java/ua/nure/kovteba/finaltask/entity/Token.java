package ua.nure.kovteba.finaltask.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Token {

    private Long id;

    private String token;

    private Long user;

}
