package com.example.javafx_project.entities;


import java.io.Serializable;
import java.util.Date;


public class Car implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String brand;
    private String model;
    private String registrationNumber;
    private Date registrationDate;
    private String color;
    private FuelType fuelType;//fix enum
    private Owner owner;

    public enum FuelType {
        GASOLINE,
        DIESEL,
        ELECTRIC,
        HYBRID
    }

    public Car() {
    }

    public Car(Integer id, String brand, String model, String registrationNumber, Date registrationDate, String color, FuelType fuelType, Owner owner) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.registrationNumber = registrationNumber;
        this.registrationDate = registrationDate;
        this.color = color;
        this.fuelType = fuelType;
        this.owner = owner;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", registrationDate=" + registrationDate +
                ", color='" + color + '\'' +
                ", fuelType=" + fuelType +
                ", owner=" + owner +
                '}';
    }
}
