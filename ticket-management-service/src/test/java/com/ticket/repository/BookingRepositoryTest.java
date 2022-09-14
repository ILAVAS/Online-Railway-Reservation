package com.ticket.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.ticket.pojo.Booking;

@SpringBootTest
public class BookingRepositoryTest {
	
	@Mock
	private BookingRepository bookingRepository;

	@Test
	public void testSaveBooking() {
		
		Booking booking = new Booking();
		booking.setBookingId(10);
		booking.setName("ilavarasi");
		booking.setAge(24);
		booking.setGender("female");
		booking.setDateOfJourney(" ");
		booking.setSeatType("aCChairClass");
		booking.setTrainId(11);
		
		when(bookingRepository.save(booking)).thenReturn(booking);
		assertEquals("ilavarasi", booking.getName());
		
	}
	
	@Test
	public void testFindAllBooking() {
		
		Booking booking = new Booking();
		booking.setBookingId(10);
		booking.setName("ilavarasi");
		booking.setAge(24);
		booking.setGender("female");
		booking.setDateOfJourney( "");
		booking.setSeatType("aCChairClass");
		booking.setTrainId(11);
		
		Booking booking1 = new Booking();
		booking1.setBookingId(10);
		booking1.setName("ilavarasi");
		booking1.setAge(24);
		booking1.setGender("female");
		booking1.setDateOfJourney("");
		booking1.setSeatType("aCChairClass");
		booking1.setTrainId(11);
		
		Booking booking2 = new Booking();
		booking2.setBookingId(10);
		booking2.setName("ilavarasi");
		booking2.setAge(24);
		booking2.setGender("female");
		booking2.setDateOfJourney("");
		booking2.setSeatType("aCChairClass");
		booking2.setTrainId(11);
		
		List<Booking> allBooking = new ArrayList<>();
		allBooking.add(booking);
		allBooking.add(booking1);
		allBooking.add(booking2);
		
		when(bookingRepository.findAll()).thenReturn(allBooking);
		assertEquals(3, allBooking.size());
	}
	
	@Test
	public void testFindBookingById() {
		
		Booking booking = new Booking();
		booking.setBookingId(10);
		booking.setName("ilavarasi");
		booking.setAge(24);
		booking.setGender("female");
		booking.setDateOfJourney("");
		booking.setSeatType("aCChairClass");
		booking.setTrainId(11);
		
		Optional<Booking> optionalBooking = Optional.of(booking);
		when(bookingRepository.findById(10)).thenReturn(optionalBooking);
		assertEquals("ilavarasi", booking.getName());
	}
	
}
