package com.flightReservation.service;

import java.util.List;
import java.util.Optional;

import com.flightReservation.model.City;

public interface CityService {
	public List<City> findAll();
    public City findById(int city_id);
}
