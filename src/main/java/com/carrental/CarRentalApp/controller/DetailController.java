package com.carrental.CarRentalApp.controller;
import com.carrental.CarRentalApp.service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DetailController {
    @Autowired
    private DetailService detailService;

    @GetMapping("/detail/{orderId}")
    @ResponseBody  //JSON
    public ResponseEntity<Object> getRentalDetails(@PathVariable Long orderId) {
        Object rentalDetails = detailService.getRentalDetailsByOrderId(orderId);
        if (rentalDetails instanceof String) {
            return ResponseEntity.status(404).body(rentalDetails);
        }
        return ResponseEntity.ok(rentalDetails);
    }
}
