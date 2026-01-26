package com.carrental.CarRentalApp.command;
import com.carrental.CarRentalApp.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeletePayment implements Execution {
    private final PaymentRepository paymentRepository;
    private Long id;

    @Autowired
    public DeletePayment(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public void execute() {
        if(id != null) {
            paymentRepository.deleteById(id);
        }
    }
    public void setId(Long id) {
        this.id = id;
    }
    public PaymentRepository getPaymentRepository() {
        return paymentRepository;
    }
}
