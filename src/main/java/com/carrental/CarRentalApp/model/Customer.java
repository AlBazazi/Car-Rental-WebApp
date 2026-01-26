package com.carrental.CarRentalApp.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phoneNumber;
    private String nic;
    private String email;
    @OneToMany(mappedBy = "customer")
    private List<RentalOrder> rentalOrders;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getNic() { return nic; }
    public void setNic(String nic) { this.nic = nic; }

    public List<RentalOrder> getRentalOrders() { return rentalOrders; }
    public void setRentalOrders(List<RentalOrder> rentalOrders) { this.rentalOrders = rentalOrders; }

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
}
