package com.carrental.CarRentalApp.controller;
import com.carrental.CarRentalApp.mediator.AdminMediator;
import com.carrental.CarRentalApp.model.*;
import com.carrental.CarRentalApp.repository.CarRepository;
import com.carrental.CarRentalApp.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired private AdminMediator adminMediator;
    @Autowired private PaymentRepository paymentRepository;
    @Autowired private CarRepository carRepository;


    @GetMapping
    public String adminPage(Model model) {
        model.addAttribute("cars", adminMediator.getAllCars());
        model.addAttribute("customers", adminMediator.getAllCustomers());
        model.addAttribute("orders", adminMediator.getAllOrders());
        model.addAttribute("payments", paymentRepository.findAll());
        return "main/admin";
    }
    @GetMapping("/dashboard")
    public String redirectDashboard() {
        return "redirect:/admin";
    }
    @GetMapping("/car/add")
    public String addCarForm(Model model) {
        Car c = new Car();
        model.addAttribute("car", c);
        adminMediator.updateObserver(c);
        return "subforms/car_form";
    }

    @PostMapping("/car/save")
    public String saveCar(@ModelAttribute Car car) {
        adminMediator.saveCar(car);
        return "redirect:/admin";
    }

    @GetMapping("/car/edit/{id}")
    public String editCarForm(@PathVariable Long id, Model model) {
        adminMediator.findCarById(id).ifPresent(c -> model.addAttribute("car", c));
        return "subforms/car_form";
    }

    @GetMapping("/car/delete/{id}")
    public String deleteCar(@PathVariable Long id) {
        adminMediator.deleteCar(id);
        return "redirect:/admin";
    }

    @GetMapping("/customer/add")
    public String addCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "subforms/customer_form";
    }

    @PostMapping("/customer/save")
    public String saveCustomer(@ModelAttribute Customer customer) {
        adminMediator.saveCustomer(customer);
        return "redirect:/admin";
    }

    @GetMapping("/customer/edit/{id}")
    public String editCustomerForm(@PathVariable Long id, Model model) {
        adminMediator.findCustomerById(id).ifPresent(c -> model.addAttribute("customer", c));
        return "subforms/customer_form";
    }

    //@GetMapping("/customer/delete/{id}")
    //public String deleteCustomer(@PathVariable Long id) {
    //    adminMediator.deleteCustomer(id);
    //    return "redirect:/admin";
    //}

    @GetMapping("/order/edit/{id}")
    public String editOrderForm(@PathVariable Long id, Model model) {
        adminMediator.findOrderById(id).ifPresent(o -> model.addAttribute("order", o));
        return "subforms/order_form";
    }
    //@GetMapping("/payment/delete/{id}")
    //public String deletePayment(@PathVariable Long id) {
    //    adminMediator.deletePayment(id);
    //    return "redirect:/admin";
    //}

    @PostMapping("/order/save")
    public String saveOrder(@ModelAttribute RentalOrder order) {
        adminMediator.saveOrder(order);
        return "redirect:/admin";
    }

    //@GetMapping("/order/delete/{id}")
    //public String deleteOrder(@PathVariable Long id) {
    //    adminMediator.deleteOrder(id);
    //    return "redirect:/admin";
    //}

    @GetMapping("/factor")
    public String showFactorForm() {
        return "subforms/factor_form";
    }

    @PostMapping("/factor/save")
    public String saveFactor(
            @RequestParam("state") String state,
            @RequestParam("model") String model,
            @RequestParam("factor") int factor) {
        Car car = carRepository.findFirstByMakeAndModel(state, model);
        if (car != null) {
            car.setPrice(factor);
            carRepository.save(car);
            return "redirect:/admin";
        }
        return "redirect:/state/failure";
    }
}
