package com.flightReservation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightReservation.model.City;
import com.flightReservation.repository.CityRepository;

@Service
public class CityServiceImpl implements CityService {
	@Autowired
	CityRepository cityRepository;

	@Override
	public List<City> findAll() {
		return cityRepository.findAll();
	}

	@Override
	public City findById(int city_id) {
		return cityRepository.getOne(city_id);
	}
	
}
