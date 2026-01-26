package com.carrental.CarRentalApp.controller;
import com.carrental.CarRentalApp.model.*;
import com.carrental.CarRentalApp.repository.*;
import com.carrental.CarRentalApp.service.*;
import jakarta.mail.SendFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/rental")
public class RentalOrderController {
    @Autowired
    private RentalOrderService rentalOrderService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private CarService carService;

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private RentalOrderRepository rentalOrderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CarRepository carRepository;

    @GetMapping("/pay")
    public String showPaymentForm(@RequestParam Long orderId, Model model) {
        RentalOrder order = rentalOrderService.getById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        model.addAttribute("order", order);
        return "main/payment";
    }

    @PostMapping("/pay")
    public String processPayment(
            @RequestParam Long orderId,
            @RequestParam String paymentMethod,
            Model model
    ) {
        RentalOrder order = rentalOrderService.getById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        Payment payment = new Payment();
        payment.setRentalOrder(order);
        payment.setAmountPaid(order.getTotalPrice());
        payment.setPaymentMethod(paymentMethod);
        payment.setPaymentDate(LocalDateTime.now());

        // Sent Email to Customer
        if(emailService.sendBookingConfirmation(order, order.getEmail()) == 0) {
            rentalOrderService.cleanUpAfterFailedEmail(order);
            return "redirect:/rental/failure?error=emailnotfound";
        }

        // Add model attributes required by your success page
        String carName = order.getCar().getMake() + " " + order.getCar().getModel();
        model.addAttribute("id", order.getId());
        model.addAttribute("car", carName);
        model.addAttribute("daysRented", order.getNumberOfDays());
        model.addAttribute("cost", order.getTotalPrice());

        paymentRepository.save(payment);
        return "state/success";
    }

    @GetMapping("/success")
    public String rentalSuccess() {
        return "state/success";
    }

    @GetMapping("/failure")
    public String rentalFailure(@RequestParam(required = false) String error, Model model) {
        model.addAttribute("errorMessage", error != null ? error : "An unknown error occurred.");
        return "state/failure";
    }

    @GetMapping("/models")
    @ResponseBody  // This will return JSON
    public List<String> getCarModels(@RequestParam String make) {
        return rentalOrderService.findByMakeAndQuantityGreaterThanAndAvailableIsTrue(make);
    }

    @PostMapping("/create")
    public String createRentalOrder(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String phoneNumber,
            @RequestParam String nic,
            @RequestParam String make,
            @RequestParam String model,
            @RequestParam int numberOfDays,
            @RequestParam String startDate,
            Model m
    ) {
        try {
            LocalDate start = LocalDate.parse(startDate);
            RentalOrder ro = rentalOrderService.createRentalOrder(name, email, phoneNumber, nic, make, model,
                    numberOfDays, start);
            String car = make+ " "+model;
            m.addAttribute("id",ro.getId());
            m.addAttribute("car",car);
            m.addAttribute("daysRented",numberOfDays);
            m.addAttribute("cost",ro.getTotalPrice());
            return "redirect:/rental/pay?orderId=" + ro.getId();
        } catch (Exception e) {
            String errorMessage = e.getMessage().replaceAll(" ", "%20");  // URL encode spaces
            return "redirect:/rental/failure?error=" + errorMessage;
        }
    }

    @GetMapping("/price")
    @ResponseBody
    public Map<String, Object> getPrice(
            @RequestParam String make,
            @RequestParam String model) {

        Double price = carRepository.findPriceByMakeAndModel(make, model);
        if (price == null) {
            price = 0.0;
        }
        Map<String, Object> response = new HashMap<>();
        response.put("price", price);
        return response;
    }

}
