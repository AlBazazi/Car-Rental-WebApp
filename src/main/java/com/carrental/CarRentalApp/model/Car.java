package com.carrental.CarRentalApp.model;

import jakarta.persistence.*;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String make;
    private String model;
    private int quantity;
    private boolean available;
    private int price;

    // Constructor
    public Car() {}
    public Car(String make, String model, int quantity, boolean available, int price) {
        this.make = make;
        this.model = model;
        this.quantity = quantity;
        this.available = available;
        this.price=price;
    }
    public Car(String make, String model, int quantity, boolean available) {
        this.make = make;
        this.model = model;
        this.quantity = quantity;
        this.available = available;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMake() { return make; }
    public void setMake(String make) { this.make = make; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    public int getPrice() {return price;}
    public void setPrice(int price) {this.price = price;}
}
