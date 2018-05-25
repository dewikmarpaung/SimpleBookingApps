package com.flightReservation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightReservation.model.Booking;
import com.flightReservation.repository.BookingRepository;

@Service
public class BookingServiceImpl implements BookingService {
	@Autowired
	BookingRepository bookingRepository;

	@Override
	public List<Booking> findAll() {
		return bookingRepository.findAll();
	}

	@Override
	public Booking findById(int booking_id) {
		return bookingRepository.getOne(booking_id);
	}

	@Override
	public void save(Booking booking) {
		bookingRepository.save(booking);
		
	}

}
