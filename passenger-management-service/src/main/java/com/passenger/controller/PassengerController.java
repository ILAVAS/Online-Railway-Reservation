package com.passenger.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.passenger.dto.LoginRequest;
import com.passenger.dto.LoginResponse;
import com.passenger.pojo.Passenger;
import com.passenger.service.PassengerService;
import com.passenger.service.SequenceGeneratorService;

@RestController
@RequestMapping("/passenger")
public class PassengerController {
	
	Logger logger = LoggerFactory.getLogger(PassengerController.class);

	@Autowired
	private PassengerService passengerService;
	

	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;
	

	@PostMapping("/add")
	public ResponseEntity<Passenger> addPassenger(@RequestBody Passenger passenger) {
		
	//	logger.info("addPassenger method from Passenger controller is accessed");

		passenger.setPassengerId(sequenceGeneratorService.generateSequence(Passenger.SEQUENCE_NAME));

		Passenger newPassenger = passengerService.savePassenger(passenger);
		ResponseEntity<Passenger> responseEntity = new ResponseEntity<>(newPassenger, HttpStatus.OK);
		return responseEntity;
	}

	@GetMapping("/all")
	public List<Passenger> fetchAllPassenger() {
		
		logger.info("fetchAllPassenger method from Passenger controller is accessed");

		List<Passenger> allPassenger = passengerService.getAllPassenger();
		return allPassenger;
	}

	@GetMapping("/find/{passengerId}")
	public ResponseEntity<Passenger> fetchById(@PathVariable("passengerId") int passengerId) {
		
		logger.info("fetchBYId method from Passenger controller is accessed");

		ResponseEntity<Passenger> reponseEntity = null;
		Passenger passenger = passengerService.getPassengerById(passengerId);
		reponseEntity = new ResponseEntity<>(passenger, HttpStatus.OK);
		return reponseEntity;
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> signin(@RequestBody LoginRequest loginRequest) {
		
		logger.info("signIn method from Passenger controller is accessed");

		Passenger passenger = passengerService.doLogin(loginRequest.getUserName(), loginRequest.getPassword());

		LoginResponse loginResponse = new LoginResponse();
		loginResponse.setPassengerId(passenger.getPassengerId());
		loginResponse.setFirstName(passenger.getFirstName());
		loginResponse.setLastName(passenger.getLastName());
		loginResponse.setAge(passenger.getAge());
		loginResponse.setPhone(passenger.getPhone());
		loginResponse.setLocation(passenger.getLocation());
		loginResponse.setUserName(passenger.getUserName());
		loginResponse.setEmail(passenger.getEmail());
		ResponseEntity<LoginResponse> responseEntity = new ResponseEntity<>(loginResponse, HttpStatus.OK);
		return responseEntity;
	}

	@PutMapping("/update")
	public ResponseEntity<Passenger> updatePassenger(@RequestBody Passenger passenger) {
		
		logger.info("updatePassenger method from Passenger controller is accessed");

		Passenger updatedPassenger = passengerService.updatePassenger(passenger);
		ResponseEntity<Passenger> responseEntity = new ResponseEntity<>(updatedPassenger, HttpStatus.OK);
		return responseEntity;
	}

	@DeleteMapping("/delete/{passengerId}")
	public ResponseEntity<String> deletePassengerById(@PathVariable("passengerId") int passengerId) {
		
		logger.info("deletePassenger method from Passenger controller is accessed");

		passengerService.deletePassengerById(passengerId);
		ResponseEntity<String> responseEntity = new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
		return responseEntity;
	}

	@GetMapping("/bylocation/{location}")
	public List<Passenger> fetchPassengerbyLocation(@PathVariable("location") String location) {
		
		logger.info("passengerByLocation method from Passenger controller is accessed");

		List<Passenger> passengerByLocation = passengerService.getAllPassengerByLocation(location);
		return passengerByLocation; 
	}

	@GetMapping("/byusername/{username}")
	public Optional<Passenger> fetchPassengerbyUserName(@PathVariable("username") String userName) {
		
		logger.info("passengerByUsername method from Passenger controller is accessed");

		Optional<Passenger> passengerByUserName = passengerService.getAllPassengerByUserName(userName);
		return passengerByUserName; 
	}

	@GetMapping("/byphone/{phone}")
	public Optional<Passenger> fetchPassengerbyPhone(@PathVariable("phone") long phone) {
		
		logger.info("passengerByLocation method from Passenger controller is accessed");

		Optional<Passenger> passengerByPhone = passengerService.getAllPassengerByPhone(phone);
		return passengerByPhone;
	}

}
