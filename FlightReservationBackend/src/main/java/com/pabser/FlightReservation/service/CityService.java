package com.pabser.FlightReservation.service;

import java.util.List;

import com.pabser.FlightReservation.model.City;

public interface CityService {

	City addCity(City City);

	City findCityById(int id_City);

	List<City> getAllCity();

	City deleteCity(int id_City);

	City updateCity(City City);

}
