package com.flightReservation.service;

import java.util.List;

import com.flightReservation.model.Booking;

public interface BookingService {
	public List<Booking> findAll();
    public Booking findById(int booking_id);
    public void save (Booking booking);
}
