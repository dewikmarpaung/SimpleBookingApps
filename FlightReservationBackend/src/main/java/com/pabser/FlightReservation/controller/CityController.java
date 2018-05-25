package com.pabser.FlightReservation.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pabser.FlightReservation.model.City;
import com.pabser.FlightReservation.repository.CityRepository;
@Controller
public class CityController {
	@Autowired
	CityRepository cRepo;
	
	@RequestMapping(value = "/list-city")
	public String homeCity(HttpServletRequest request, Model model) {
		int userId = 0;
		try {
			userId = Integer.valueOf(request.getSession().getAttribute("userId").toString());
			//System.out.println("id-search : " + userId);
		} catch (NullPointerException npe) {
			return "redirect:/login";
		}
		System.out.println("test");
		model.addAttribute("allCity", cRepo.findAll());

		return "city";
	}
	
	@RequestMapping(value = "/create-city")
	public String createCity(Model model) {
		model.addAttribute("city", new City());
		return "createCity";
	}

	@RequestMapping(value = "/save-city", method = RequestMethod.POST)
	public String saveCitySubmit(HttpServletRequest request, Model model, City city) {
		
		model.addAttribute("city", cRepo.saveAndFlush(city));
		return "redirect:/list-city";
	}
	
	@RequestMapping(value = "/edit-city/{id}")
	public String editAir(@PathVariable Integer id, Model model) {
		model.addAttribute("city", cRepo.findById(id));
		return "editCity";

	}
	
	@RequestMapping(value = "/delete-city/{id}")
	public String deleteCity(@PathVariable Integer id) {
		System.out.println(id);
		cRepo.deleteById(id);
		return "redirect:/list-city";

	}
	
	
}
