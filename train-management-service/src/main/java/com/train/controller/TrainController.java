package com.train.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.train.dto.ResponseTemplate;
import com.train.pojo.Train;
import com.train.service.SequenceGeneratorService;
import com.train.service.TrainService;

@RestController
@RequestMapping("/train")
@CrossOrigin(origins = "localhost", maxAge = 3600)
public class TrainController {
	
	Logger logger = LoggerFactory.getLogger(TrainController.class);
	
	@Autowired
	private TrainService trainService;
	

	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;
	
	@PostMapping("/add")
	public ResponseEntity<Train> addTrain(@RequestBody Train train) {
		
	//	logger.info("addTrain method from Train controller is accessed");
		train.setTrainId(sequenceGeneratorService.generateSequence(Train.SEQUENCE_NAME));

		Train newTrain = trainService.saveTrain(train);
		ResponseEntity<Train> responseEntity = new ResponseEntity<>(newTrain, HttpStatus.OK);
		return responseEntity;
	}
	
	@GetMapping("/all")
	public List<Train> fetchAllTrain() {
		
		logger.info("fetchAllTrain method from Train controller is accessed"); 

		List<Train> allTrain = trainService.getAllTrain();
		return allTrain;
	}
	
	@GetMapping("/fare/{trainId}")
	public ResponseTemplate fetchById(@PathVariable("trainId") int trainId) {
		
		logger.info("TrainByFare method from Train controller is accessed");

		ResponseTemplate trainById = trainService.getTrainByFare(trainId);
		return trainById;
	}
	
	 @GetMapping("/find/{trainId}")
		public ResponseEntity<Object> fetchTrainById(@PathVariable("trainId") int trainId) {
		 
		 logger.info("TrainById method from Train controller is accessed");
			
			ResponseEntity<Object> responseEntity = null;		
			Train trainById = trainService.getTrainById(trainId);
			responseEntity = new ResponseEntity<>(trainById,HttpStatus.OK);				
			return responseEntity;
		}
	
	@PutMapping("/update")
	public ResponseEntity<Train> updateTrain(@RequestBody Train train) {
		
		logger.info("updateTrain method from Train controller is accessed");

		Train updatedTrain = trainService.updateTrain(train);
		ResponseEntity<Train> responseEntity = new ResponseEntity<>(updatedTrain, HttpStatus.OK);
		return responseEntity;
	}
	
	@DeleteMapping("/delete/{trainId}") 
	public ResponseEntity<String> deletePassengerById(@PathVariable("trainId") int trainId) {
		
		logger.info("deleteTrain method from Train controller is accessed");

		trainService.deleteTrainById(trainId);
		ResponseEntity<String> responseEntity = new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
		return responseEntity;
	}
	
	@GetMapping("/byname/{trainName}")
	public ResponseEntity<Train> fetchTrainByName(@PathVariable("trainName") String trainName) {
		
		logger.info("TrainByName method from Train controller is accessed");
		
		Train trainByName = trainService.getAllTrainByTrainName(trainName);
		ResponseEntity<Train> responseEntity = new ResponseEntity<>(trainByName, HttpStatus.OK);
		return responseEntity;
		
	}
	
	@GetMapping("/byroute/{source}/{destination}")
	public List<Train> fetchAllTrainByRoute(@PathVariable("source") String source, @PathVariable("destination") String destination) {
		
		List<Train> trains = trainService.getAllTrainWithinRange(source, destination);
		return trains;
	}
}
