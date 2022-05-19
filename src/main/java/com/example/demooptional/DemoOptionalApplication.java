package com.example.demooptional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class DemoOptionalApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoOptionalApplication.class, args);

		System.out.println("Running DemoOptionalApplication");

		Person person = getPerson();

		boolean carIsAFerrari = person != null
				&& person.getHouse() != null
				&& person.getHouse().getGarage() != null
				&& person.getHouse().getGarage().getCar() != null
				&& person.getHouse().getGarage().getCar().getBrand() == Brand.FERRATI;

		if (carIsAFerrari) {

			System.out.println("Frank owns a Ferrari.");
		} else {
			System.out.println("Frank does not own a car.");
		}

		Optional<Person> optionalPerson = getOptionalPersion();

		Boolean carIsAPorche = optionalPerson
				.map(Person::getHouse)
				.map(House::getGarage)
				.map(Garage::getCar)
				.map(c -> c.getBrand() == Brand.PORCHE)
				.orElse(Boolean.FALSE);

		if (carIsAPorche) {

			System.out.println("Pete owns a Porche.");
		} else {
			System.out.println("Pete does not own a car.");
		}
	}

	private static Person getPerson() {

		return new Person();
	}

	private static Optional<Person> getOptionalPersion() {

		Car car = new Car();
		car.setBrand(Brand.PORCHE);

		Garage garage = new Garage();
		garage.setCar(car);

		House house = new House();
		house.setGarage(garage);

		Person person = new Person();
		person.setHouse(house);
		return Optional.of(person);
	}

}
