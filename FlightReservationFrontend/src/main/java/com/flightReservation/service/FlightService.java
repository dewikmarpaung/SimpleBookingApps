package com.flightReservation.service;

import java.util.List;

import com.flightReservation.model.Flight;

public interface FlightService {
	public List<Flight> findAll();
    public Flight findById(int flight_id);
    public void save (Flight flight);
}
