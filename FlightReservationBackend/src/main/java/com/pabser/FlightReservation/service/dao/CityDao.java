package com.pabser.FlightReservation.service.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pabser.FlightReservation.model.City;
import com.pabser.FlightReservation.model.Flight;
import com.pabser.FlightReservation.repository.CityRepository;
import com.pabser.FlightReservation.service.CityService;

@Service("CityService")
public class CityDao implements CityService {

	@Autowired
	CityRepository cRepo;

	@Override
	public City addCity(City city) {
		return cRepo.save(city);
	}

	@Override
	public City findCityById(int id_City) {
		return cRepo.getOne(id_City);
	}

	@Override
	public List<City> getAllCity() {
		List<City> cList = cRepo.findAll();
		if (cList != null) {
			return cList;
		} else {
			return null;
		}
	}

	@Override
	public City deleteCity(int id_City) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public City updateCity(City city) {
		// TODO Auto-generated method stub
		return null;
	}

}
