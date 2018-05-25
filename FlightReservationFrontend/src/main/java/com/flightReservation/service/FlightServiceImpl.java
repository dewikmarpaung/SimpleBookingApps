package com.flightReservation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightReservation.model.Flight;
import com.flightReservation.repository.FlightRepository;

@Service
public class FlightServiceImpl implements FlightService {
	@Autowired
	FlightRepository flightRepository;

	@Override
	public List<Flight> findAll() {
		return flightRepository.findAll();
	}
	
	@Override
	public Flight findById(int flight_id) {
		return flightRepository.getOne(flight_id);
	}

	@Override
	public void save(Flight flight) {
		flightRepository.save(flight);
		
	}
}
