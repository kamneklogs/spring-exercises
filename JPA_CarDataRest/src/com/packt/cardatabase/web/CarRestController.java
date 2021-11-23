package com.packt.cardatabase.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.packt.cardatabase.domain.Car;
import com.packt.cardatabase.services.CarService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class CarRestController {
	@Autowired
	private CarService carService;

	@RequestMapping("/cars")
	public Iterable<Car> getCars() {
		return carService.getCars();
	}

	@GetMapping("/cars/{id}")
	public Car one(@PathVariable Long id) {

		return carService.getCar(id).get();
	}

	@PostMapping("/cars")
	public void postMethodName(@RequestBody Car car) {

		carService.addCar(car);
	}

	@DeleteMapping("/cars")
	void deleteEmployee(@RequestBody Car car) {
		carService.deleteCar(car);
	}

}