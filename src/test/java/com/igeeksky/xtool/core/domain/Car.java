package com.igeeksky.xtool.core.domain;

import java.util.Objects;

/**
 * @author patrick
 * @since 0.0.4 2024/6/3
 */
public class Car {

    private String brand;

    private String model;

    private String color;

    private int year;

    public Car() {
    }

    public Car(String brand, String model, String color, int year) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.year = year;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car car)) return false;

        return getYear() == car.getYear() && Objects.equals(getBrand(), car.getBrand()) && Objects.equals(getModel(), car.getModel()) && Objects.equals(getColor(), car.getColor());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getBrand());
        result = 31 * result + Objects.hashCode(getModel());
        result = 31 * result + Objects.hashCode(getColor());
        result = 31 * result + getYear();
        return result;
    }
}
