package com.passenger.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.passenger.exception.AuthenticationFailedException;
import com.passenger.exception.UserNotFoundException;
import com.passenger.pojo.Passenger;
import com.passenger.repository.PassengerRepository;

@SpringBootTest
public class PassengerServiceTest {
	
	@InjectMocks
	private PassengerService passengerService = new PassengerServiceImpl();
	
	@Mock
	private PassengerRepository passengerRepository;
	
	@Test
	public void testGetPassengerById() {
		
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
		
		Passenger myPassenger = passengerService.getPassengerById(10);
		assertEquals("ilavas", myPassenger.getUserName());
	}
	
	@Test
	public void testGetPassengerByIdWithEXception() {
		
		when(passengerRepository.findById(10)).thenThrow(UserNotFoundException.class);
		assertThrows(UserNotFoundException.class,() ->passengerService.getPassengerById(10));
	}
	
	@Test
	public void testAllPassengerByLocation() {
		Passenger passenger = new Passenger();
		passenger.setPassengerId(10);
		passenger.setFirstName("ilavarasi");
		passenger.setLastName("s");
		passenger.setAge(24);
		passenger.setGender("female");
		passenger.setPhone(998078);
		passenger.setLocation("bangalore");
		passenger.setUserName("ilavarasi");
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
		
		when(passengerRepository.findByLocation("bangalore")).thenReturn(passengerList);
		List<Passenger> passengerByLocation = passengerService.getAllPassengerByLocation("bangalore");
		assertEquals(3, passengerByLocation.size());
	}
	
	@Test
	public void testGetPassengerByLocationWithException() {
		
		when(passengerRepository.findByLocation("bangalore")).thenThrow(UserNotFoundException.class);
		assertThrows(UserNotFoundException.class, () -> passengerService.getAllPassengerByLocation("bangalore"));
	}
	
	@Test
	public void testGetPassengerByUserName() {
		
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
		when(passengerRepository.findByUserName("ilavas")).thenReturn(optionalPassenger);
		
		passengerService.getAllPassengerByUserName("ilavas");
		assertEquals("ilavas", passenger.getUserName());
		
	}
	
	@Test
	public void testGetPassengerByUserNameWithException() {
		
		when(passengerRepository.findByUserName("srinivas")).thenThrow(UserNotFoundException.class);
		assertThrows(UserNotFoundException.class, () -> passengerService.getAllPassengerByUserName("srinivas"));
	}
	
	@Test
	public void testGetPassengerByPhone() {
		Passenger passenger = new Passenger();
		passenger.setPassengerId(10);
		passenger.setFirstName("ilavarasi");
		passenger.setLastName("s");
		passenger.setAge(24);
		passenger.setGender("female");
		passenger.setPhone(998078);
		passenger.setLocation("bangalore");
		passenger.setUserName("ilavarasi");
		passenger.setEmail("ila@mail.com");
		passenger.setPassword("12345");
		
		Optional<Passenger> optionalPassenger = Optional.of(passenger);
		when(passengerRepository.findByPhone(998078)).thenReturn(optionalPassenger);
		
		passengerService.getAllPassengerByPhone(998078);
		assertEquals(998078, passenger.getPhone());
		
	}
	
	@Test
	public void testGetPassengerByPhoneWithException() {
		
		when(passengerRepository.findByPhone(998078)).thenThrow(UserNotFoundException.class);
		assertThrows(UserNotFoundException.class, () -> passengerService.getAllPassengerByPhone(998078));
	}
	
	@Test
	public void testGetAllPassenger() {
		Passenger passenger = new Passenger();
		passenger.setPassengerId(10);
		passenger.setFirstName("ilavarasi");
		passenger.setLastName("s");
		passenger.setAge(24);
		passenger.setGender("female");
		passenger.setPhone(998078);
		passenger.setLocation("bangalore");
		passenger.setUserName("ilavarasi");
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
		List<Passenger> passengers = passengerService.getAllPassenger();
		assertEquals(3, passengers.size());
	}
	
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
		Passenger newPassenger = passengerService.savePassenger(passenger);
		assertEquals("ilavas", newPassenger.getUserName());
		verify(passengerRepository,times(1)).save(passenger);
	}
	
	@Test
	public void testUpdatePassenger() {
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
		passengerService.updatePassenger(passenger);
		verify(passengerRepository,times(1)).findById(10);
		verify(passengerRepository,times(1)).save(passenger);
	}
	
	@Test
	public void testUpdatePassengerWithException() {
		
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
		
		when(passengerRepository.findById(10)).thenThrow(UserNotFoundException.class);
		assertThrows(UserNotFoundException.class, () -> passengerService.updatePassenger(passenger));
	}
	
	@Test
	public void testDeletePassengerById( ) {
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
		passengerService.deletePassengerById(10);
		verify(passengerRepository,times(1)).findById(10);
		verify(passengerRepository,times(1)).deleteById(10);
		
	}
	
	@Test
	public void testDeletePassengerByIdWithException() {
		
		when(passengerRepository.findById(10)).thenThrow(UserNotFoundException.class);
		assertThrows(UserNotFoundException.class, () -> passengerService.deletePassengerById(10));
	}
	
	@Test
	public void testDoLogin() {
		
		Passenger passenger = new Passenger();
		passenger.setPassengerId(10);
		passenger.setUserName("ilavas");
		passenger.setPassword("12345");
		
		when(passengerRepository.login(passenger.getUserName(), passenger.getPassword())).thenReturn(passenger);
		assertEquals("ilavas", passenger.getUserName());
		passenger = passengerService.doLogin(passenger.getUserName(), passenger.getPassword());
		verify(passengerRepository,times(1)).login(passenger.getUserName(), passenger.getPassword());
	}
	
	@Test
	public void testDoLoginWithException() {
		
		Passenger passenger = new Passenger();
		passenger.setPassengerId(10);
		passenger.setUserName("ilavas");
		passenger.setPassword("12345");
		
		when(passengerRepository.login(passenger.getUserName(), passenger.getPassword())).thenThrow(AuthenticationFailedException.class);
		assertThrows(AuthenticationFailedException.class,()->passengerService.doLogin(passenger.getUserName(), passenger.getPassword()));
	}
}
