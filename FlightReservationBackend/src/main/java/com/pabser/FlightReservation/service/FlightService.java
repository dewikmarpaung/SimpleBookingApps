package com.pabser.FlightReservation.service;

import java.util.List;

import com.pabser.FlightReservation.model.*;

public interface FlightService {

	Flight addFlight(Flight Flight);

	Flight findFlightById(int id_Flight);

	List<Flight> getAllFlight();

	Flight deleteFlight(int id_Flight);

	Flight updateFlight(Flight Flight);
}
