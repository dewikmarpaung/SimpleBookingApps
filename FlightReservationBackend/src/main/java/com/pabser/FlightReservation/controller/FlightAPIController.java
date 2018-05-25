package com.pabser.FlightReservation.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.pabser.FlightReservation.service.*;
import com.pabser.FlightReservation.tools.ModelToResponseMapper;
import com.pabser.FlightReservation.tools.Response;
import com.pabser.FlightReservation.tools.View;
import com.fasterxml.jackson.annotation.JsonView;
import com.pabser.FlightReservation.model.*;
import com.pabser.FlightReservation.repository.CityRepository;
import com.pabser.FlightReservation.repository.FlightRepository;
import com.pabser.FlightReservation.repository.PaymentRepository;
import com.pabser.FlightReservation.repository.SeatCategoryRepository;

@Controller
@RestController
public class FlightAPIController {

	@Autowired
	FlightService fService;

	@Autowired
	FlightRepository fRepo;

	@Autowired
	CityRepository cRepo;
	
	@Autowired
	CityService cService;
	
	@Autowired
	SeatCategoryRepository sRepo;
	
	@Autowired
	SeatService sService;
	
	@Autowired
	PaymentRepository pRepo;
	
	/*@JsonView(View.Public.class)
	@RequestMapping(value = "api-flight", method = RequestMethod.GET)
	public Response<List<Flight>> getListAllFlight() {
		List<Flight> flights = fService.getAllFlight();
		return ModelToResponseMapper.mapThisSuccess(flights);
	}*/
	
	//FLIGHT API
	@RequestMapping(value = "api-flight", method=RequestMethod.GET)
	@ResponseBody
	public List<Flight> getListAllFlight() {
		List<Flight> flights = fService.getAllFlight();
		return flights;
	}

	@JsonView(View.Public.class)
	@RequestMapping(value = "api-flight/{id}", method = RequestMethod.GET)
	public ResponseEntity<Flight> getFlightById(@PathVariable Integer id, @ModelAttribute Flight flight) {
		Flight flight1 = fService.findFlightById(id);
		if(flight1 == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Flight>(flight1,HttpStatus.OK);
	}

	@JsonView(View.Public.class)
	@RequestMapping(value = "api-create-flight", method = RequestMethod.POST)
	public Flight createFlight(@RequestBody Flight flight) {
		fService.addFlight(flight);
		return flight;

	}
	
	//PAYMENT API
	@JsonView(View.Public.class)
	@RequestMapping(value = "api-create-payment", method = RequestMethod.POST)
	public Payment createPayment(@RequestBody Payment payment) {
		pRepo.saveAndFlush(payment);
		return payment;
	}

	//CITY API
	@RequestMapping(value = "api-city", method = RequestMethod.GET)
	@ResponseBody
	public List<City> getListAllCity() {
		List<City> cities = cService.getAllCity();
		return cities;
	}
	
	//SEAT CATEGORY API
	@RequestMapping(value = "api-seat", method = RequestMethod.GET)
	@ResponseBody
	public List<SeatCategory> getListAllSeat() {
		List<SeatCategory> seat = sService.getAllSeat();
		return seat;
	}
}
