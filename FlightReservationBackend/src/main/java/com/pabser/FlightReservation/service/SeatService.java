package com.pabser.FlightReservation.service;

import java.util.List;

import com.pabser.FlightReservation.model.SeatCategory;

public interface SeatService {

	SeatCategory addSeat(SeatCategory Seat);

	SeatCategory findSeatById(int id_Seat);

	List<SeatCategory> getAllSeat();

	SeatCategory deleteSeat(int id_Seat);

	SeatCategory updateSeat(SeatCategory Seat);

}
