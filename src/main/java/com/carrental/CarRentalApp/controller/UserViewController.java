package com.carrental.CarRentalApp.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserViewController {
    @GetMapping("/")
    public String showHomePage() {return "main/home";
    }
    @GetMapping("/form")
    public String showForm() {return "main/form";}

    @GetMapping("/details")
    public String showDetails() {
        return "main/details";
    }
}
