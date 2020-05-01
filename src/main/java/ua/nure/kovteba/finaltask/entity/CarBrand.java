package ua.nure.kovteba.finaltask.entity;

import java.io.Serializable;
import java.util.Objects;

public class CarBrand implements Serializable {

    private Long id;

    private String brandName;

    public CarBrand() {
    }

    public CarBrand(Long id, String brandName) {
        this.id = id;
        this.brandName = brandName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarBrand carBrand = (CarBrand) o;
        return Objects.equals(id, carBrand.id) &&
                Objects.equals(brandName, carBrand.brandName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brandName);
    }

    @Override
    public String toString() {
        return "CarBrand{" +
                "id=" + id +
                ", brandName='" + brandName + '\'' +
                '}';
    }
}
