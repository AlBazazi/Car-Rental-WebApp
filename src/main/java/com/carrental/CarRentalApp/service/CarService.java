package com.carrental.CarRentalApp.service;
import com.carrental.CarRentalApp.model.Car;
import com.carrental.CarRentalApp.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CarService {
    @Autowired private CarRepository carRepository;

    public void updateQuantity(Long id) {
        Optional<Car> optionalCar = carRepository.findById(id);
        if (optionalCar.isPresent()) {
            Car car = optionalCar.get();
            car.setQuantity(car.getQuantity() + 1);
            carRepository.save(car);
        }
    }
}
