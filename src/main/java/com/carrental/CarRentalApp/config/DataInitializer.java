package com.carrental.CarRentalApp.config;
import com.carrental.CarRentalApp.factory.*;
import com.carrental.CarRentalApp.repository.CarRepository;
import com.carrental.CarRentalApp.repository.RentalOrderRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private RentalOrderRepository rentalOrderRepository;
    @Autowired
    private Audi audi;
    @Autowired
    private BMW bmw;
    @Autowired
    private Mercedes mercedes;

    private final String[] MODELS = {"Model 1", "Model 2"};

    @PostConstruct
    public void init() {
        if (carRepository.count() > 0) carRepository.deleteAll();
        boolean AVAILABILITY = true;
        int QUANTITY = 2;
        carRepository.save(audi.createCar("A4", QUANTITY, AVAILABILITY, 35000));
        carRepository.save(audi.createCar("X3", QUANTITY, AVAILABILITY, 30000));
        carRepository.save(bmw.createCar("GLL", QUANTITY, AVAILABILITY, 37000));
        carRepository.save(bmw.createCar("Series 3", QUANTITY, AVAILABILITY, 33000));
        carRepository.save(mercedes.createCar("OSS", QUANTITY, AVAILABILITY, 43000));
        carRepository.save(mercedes.createCar("XLS", QUANTITY, AVAILABILITY, 40000));
        System.out.println("******* TEST DATA ENTERED *******");
    }
}
