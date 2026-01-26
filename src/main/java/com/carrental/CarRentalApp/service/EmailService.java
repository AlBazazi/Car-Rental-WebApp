package com.carrental.CarRentalApp.service;
import com.carrental.CarRentalApp.model.RentalOrder;
import jakarta.mail.SendFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired private JavaMailSender mailSender;

    @Value("${mail.from}")
    private String fromEmail;
    public int sendBookingConfirmation(RentalOrder order, String toEmail) {
        if (toEmail == null || toEmail.isEmpty()) {
            System.out.println("***ERROR***. STRING IS EITHER NULL OR EMPTY");
            return 0;
        }
        String subject = "Your Car Rental has been Confirmed!";
        String body = new StringBuilder()
                .append("Car Rental Confirmation\n\n")
                .append("Dear Customer,\n\n")
                .append("Thank you for choosing our car rental service. Here are your booking details:\n\n")
                .append("Booking ID: ").append(order.getId()).append("\n")
                .append("Name: ").append(order.getCustomer().getName()).append("\n")
                .append("Phone: ").append(order.getCustomer().getPhoneNumber()).append("\n")
                .append("Car: ").append(order.getCar().getMake()).append(" ").append(order.getCar().getModel()).append("\n")
                .append("Number of Days: ").append(order.getNumberOfDays()).append("\n")
                .append("Start Date: ").append(order.getStartDate()).append("\n")
                .append("End Date: ").append(order.getStartDate().plusDays(order.getNumberOfDays())).append("\n")
                .append("Total Price: ").append(order.getTotalPrice()).append(" PKR\n\n")
                .append("Please bring your ID and booking confirmation when you come to collect your car.\n\n")
                .append("If you have any questions, contact us at support@carrental.com.\n\n")
                .append("Best regards,\n")
                .append("Car Rental Support Team")
                .toString();

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(toEmail);
            message.setSubject(subject);
            message.setText(body);
            mailSender.send(message);
            return 1;
        } catch (Exception e) {
            System.out.println("*****ERROR SENDING EMAIL*****: " + e.getMessage());
            return 0;
        }
    }
}