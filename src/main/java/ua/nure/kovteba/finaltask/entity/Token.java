package ua.nure.kovteba.finaltask.entity;

import java.util.Objects;

public class Token {

    private Long id;

    private String token;

    private Long user;

    public Token() {
    }

    public Token(Long id, String token, Long user) {
        this.id = id;
        this.token = token;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token1 = (Token) o;
        return Objects.equals(id, token1.id) &&
                Objects.equals(token, token1.token) &&
                Objects.equals(user, token1.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, token, user);
    }

    @Override
    public String toString() {
        return "Token{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", user=" + user +
                '}';
    }
}
