package com.carrental.CarRentalApp.model;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Details {

    private String customerName;
    private String carMake;    // Added field for car's make (company)
    private String carModel;   // Added field for car's model
    private LocalDate startDate;
    private int numberOfDays;
    private BigDecimal totalPrice;
    private String email;

    public Details(String customerName,
                   String email,
                   String carMake,
                   String carModel,
                   LocalDate startDate,
                   int numberOfDays,
                   BigDecimal totalPrice) {
        this.customerName = customerName;
        this.email=email;
        this.carMake = carMake;
        this.carModel = carModel;
        this.startDate = startDate;
        this.numberOfDays = numberOfDays;
        this.totalPrice = totalPrice;
    }

    // Getters and Setters
    public String getCustomerName() { return customerName; }
    public String getCarMake() { return carMake; }
    public String getCarModel() { return carModel; }
    public LocalDate getStartDate() { return startDate; }
    public int getNumberOfDays() { return numberOfDays; }
    public BigDecimal getTotalPrice() { return totalPrice; }
    public String getEmail() {return email;}

    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public void setCarMake(String carMake) { this.carMake = carMake; }
    public void setCarModel(String carModel) { this.carModel = carModel; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public void setNumberOfDays(int numberOfDays) { this.numberOfDays = numberOfDays; }
    public void setTotalPrice(BigDecimal totalPrice) { this.totalPrice = totalPrice; }
    public void setEmail(String email) {this.email = email;}
}
