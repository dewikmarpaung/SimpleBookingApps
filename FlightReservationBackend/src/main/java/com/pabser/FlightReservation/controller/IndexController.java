package com.pabser.FlightReservation.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pabser.FlightReservation.repository.AirlinesRepository;
import com.pabser.FlightReservation.repository.CityRepository;
import com.pabser.FlightReservation.repository.FlightRepository;
import com.pabser.FlightReservation.repository.SeatCategoryRepository;
import com.pabser.FlightReservation.repository.UserRepository;
import com.pabser.FlightReservation.service.FlightService;
import com.pabser.FlightReservation.service.UserService;
import com.pabser.FlightReservation.model.User;
import com.pabser.FlightReservation.model.Airlines;
import com.pabser.FlightReservation.model.City;
import com.pabser.FlightReservation.model.Flight;

@Controller
public class IndexController {

	@Autowired
	FlightRepository fRepo;
	@Autowired
	FlightService fService;
	@Autowired
	CityRepository cRepo;
	@Autowired
	AirlinesRepository aRepo;
	@Autowired
	SeatCategoryRepository sRepo;
	@Autowired
	UserService userService;

	@RequestMapping("/index")
	public String home() {
		return "home";
	}

	@GetMapping("/login")
	public String loginForm(Model model) {
		return "auth";
	}

	@PostMapping("/login")
	public String loginSubmit(HttpServletRequest request, Model model) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		try {
			boolean logged = userService.login(username, password);
			if (logged == true) {
				System.out.println("login");
				User user = userService.findByUsername(username);

				request.getSession().setAttribute("userId", user.getUser_id());
				return "redirect:/list-flight";
			} else {
				System.out.print("unlogin");
				return "auth";
			}
		} catch (Exception e) {
			return "auth";
		}
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		int userId = 0;
		try {
			userId = Integer.valueOf(request.getSession().getAttribute("userId").toString());
			System.out.println("id logout : " + userId);
			request.getSession().removeAttribute("userId");
			return "redirect:/index";
		} catch (NullPointerException npe) {
			return "redirect:/login";
		}
	}

	@GetMapping(value = "/list-flight")
	public String homeFlight(HttpServletRequest request, Model model) {
		int userId = 0;
		try {
			userId = Integer.valueOf(request.getSession().getAttribute("userId").toString());
			//System.out.println("id-search : " + userId);
		} catch (NullPointerException npe) {
			return "redirect:/login";
		}
		System.out.println("test");
		model.addAttribute("allflight", fRepo.findAll());

		return "flight";
	}

	@RequestMapping(value = "/create-flight")
	public String createFlight(Model model) {
		List<City> allCity = cRepo.findAll();
		for (City city : allCity) {
			System.out.print(city.getCity_id());
			System.out.print(city.getName());
		}
		List<Airlines> allAir = aRepo.findAll();
		for (Airlines airline : allAir) {
			System.out.print(airline.getAirlines_id());
			System.out.print(airline.getName());
		}
		model.addAttribute("allAirlines", allAir);
		model.addAttribute("allCity", allCity);
		model.addAttribute("allSeat", sRepo.findAll());
		model.addAttribute("flight", new Flight());
		return "createFlight";
	}

	@RequestMapping(value = "/save-flight", method = RequestMethod.POST)
	public String saveSubmit(HttpServletRequest request, Model model, Flight flight) {
		String airline = request.getParameter("airline");
		String dept = request.getParameter("departure");
		String dest = request.getParameter("destination");
		flight.setAirline(airline);
		System.out.println(airline);
		flight.setDeparture_city(dept);
		flight.setDestination_city(dest);
		model.addAttribute("flight", fRepo.saveAndFlush(flight));
		return "redirect:/list-flight";
	}

	@RequestMapping(value = "/delete-flight/{id}")
	public String deleteSubmit(@PathVariable Integer id) {
		System.out.println(id);
		fRepo.deleteById(id);
		return "redirect:/list-flight";

	}

	@RequestMapping(value = "/edit-flight/{id}")
	public String editFrom(@PathVariable Integer id, Model model) {
		Flight edFlight = fRepo.findById(id).get();
		model.addAttribute("flight", fRepo.findById(id));
		List<City> allCity = cRepo.findAll();
		List<Airlines> allAir = aRepo.findAll();
		model.addAttribute("allAirlines", allAir);
		model.addAttribute("allCity", allCity);
		model.addAttribute("allSeat", sRepo.findAll());
		return "editFlight";

	}

}
