package com.flightReservation.service;

import java.util.List;

import com.flightReservation.model.SeatCategory;

public interface SeatCategoryService {
	public List<SeatCategory> findAll();
    public SeatCategory findById(int seatId);
}
