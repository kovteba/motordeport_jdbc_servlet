package ua.nure.kovteba.finaltask.entity;

import java.util.Objects;

public class EmploymentStatus {

    private Long id;

    private Long idDriver;

    private String value;

    public EmploymentStatus() {
    }

    public EmploymentStatus(Long id, Long idDriver, String value) {
        this.id = id;
        this.idDriver = idDriver;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdDriver() {
        return idDriver;
    }

    public void setIdDriver(Long idDriver) {
        this.idDriver = idDriver;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmploymentStatus that = (EmploymentStatus) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(idDriver, that.idDriver) &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idDriver, value);
    }

    @Override
    public String toString() {
        return "EmploymentStatus{" +
                "id=" + id +
                ", idDriver=" + idDriver +
                ", value='" + value + '\'' +
                '}';
    }
}
