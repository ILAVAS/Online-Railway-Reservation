package com.passenger.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.passenger.pojo.Passenger;

@SpringBootTest
public class PassengerRepositoryTest {
	
	@Mock
	private PassengerRepository passengerRepository;

	@Test
	public void testSavePassenger() {
		
		Passenger passenger = new Passenger();
		passenger.setPassengerId(10);
		passenger.setFirstName("ilavarasi");
		passenger.setLastName("s");
		passenger.setAge(24);
		passenger.setGender("female");
		passenger.setPhone(998078);
		passenger.setLocation("bangalore");
		passenger.setUserName("ilavas");
		passenger.setEmail("ila@mail.com");
		passenger.setPassword("12345");
		
		when(passengerRepository.save(passenger)).thenReturn(passenger);
		assertEquals("ilavas", passenger.getUserName());
	}
	
	@Test
	public void testFindAllPassenger() {
		
		Passenger passenger = new Passenger();
		passenger.setPassengerId(10);
		passenger.setFirstName("ilavarasi");
		passenger.setLastName("s");
		passenger.setAge(24);
		passenger.setGender("female");
		passenger.setPhone(998078);
		passenger.setLocation("bangalore");
		passenger.setUserName("ilavas");
		passenger.setEmail("ila@mail.com");
		passenger.setPassword("12345");
		
		
		Passenger passenger1 = new Passenger();
		passenger1.setPassengerId(11);
		passenger1.setFirstName("keshu");
		passenger1.setLastName("s");
		passenger1.setAge(19);
		passenger1.setGender("female");
		passenger1.setPhone(454545);
		passenger1.setLocation("chennai");
		passenger1.setUserName("kesav");
		passenger1.setEmail("keshu@mail.com");
		passenger1.setPassword("454545");
		
		Passenger passenger2 = new Passenger();
		passenger2.setPassengerId(12);
		passenger2.setFirstName("harshitha");
		passenger2.setLastName("g");
		passenger2.setAge(18);
		passenger2.setGender("female");
		passenger2.setPhone(191919);
		passenger2.setLocation("avadi");
		passenger2.setUserName("harshi");
		passenger2.setEmail("harshi@mail.com");
		passenger2.setPassword("191919");
		
		List<Passenger> passengerList = new ArrayList<>();
		passengerList.add(passenger);
		passengerList.add(passenger1);
		passengerList.add(passenger);
		
		when(passengerRepository.findAll()).thenReturn(passengerList);
		assertEquals(3, passengerList.size());
	}
	
	@Test
	public void testFindPassengerById() {
		
		Passenger passenger = new Passenger();
		passenger.setPassengerId(10);
		passenger.setFirstName("ilavarasi");
		passenger.setLastName("s");
		passenger.setAge(24);
		passenger.setGender("female");
		passenger.setPhone(998078);
		passenger.setLocation("bangalore");
		passenger.setUserName("ilavas");
		passenger.setEmail("ila@mail.com");
		passenger.setPassword("12345");
		
		Optional<Passenger> optionalPassenger = Optional.of(passenger);
		when(passengerRepository.findById(10)).thenReturn(optionalPassenger);
		assertEquals("ilavas", passenger.getUserName());
	}
	
	@Test
	public void testLogin() {
		
		Passenger passenger = new Passenger();
		passenger.setPassengerId(10);
		passenger.setFirstName("ilavarasi");
		passenger.setLastName("s");
		passenger.setAge(24);
		passenger.setGender("female");
		passenger.setPhone(998078);
		passenger.setLocation("bangalore");
		passenger.setUserName("ilavas");
		passenger.setEmail("ila@mail.com");
		passenger.setPassword("12345");
		
		when(passengerRepository.login("ilavas", "12345")).thenReturn(passenger);
		assertEquals("ilavas", passenger.getUserName());
	}
}
