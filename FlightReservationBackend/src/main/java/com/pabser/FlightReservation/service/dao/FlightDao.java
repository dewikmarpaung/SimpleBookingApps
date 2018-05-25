package com.pabser.FlightReservation.service.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pabser.FlightReservation.model.Flight;

import com.pabser.FlightReservation.repository.FlightRepository;
import com.pabser.FlightReservation.service.FlightService;

@Service("flightService")
public class FlightDao implements FlightService {

	@Autowired
	private FlightRepository fRepo;

	@Override
	public Flight addFlight(Flight flight) {
		return fRepo.save(flight);
	}

	@Override
	public Flight findFlightById(int id_Flight) {
		return fRepo.getOne(id_Flight);
	}

	@Override
	public List<Flight> getAllFlight() {
		List<Flight> fList = fRepo.findAll();
		if (fList != null) {
			return fList;
		} else {
			return null;
		}
	}

	@Override
	public Flight deleteFlight(int id_Flight) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flight updateFlight(Flight Flight) {
		// TODO Auto-generated method stub
		return null;
	}

}
