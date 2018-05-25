package com.pabser.FlightReservation.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pabser.FlightReservation.model.Airlines;
import com.pabser.FlightReservation.repository.AirlinesRepository;

@Controller
public class AirlinesController {
	@Autowired
	AirlinesRepository aRepo;
	
	@RequestMapping(value = "/edit-airline/{id}")
	public String editAir(@PathVariable Integer id, Model model) {
		model.addAttribute("airlines", aRepo.findById(id));
		return "editAir";

	}
	
	@RequestMapping(value = "/delete-airline/{id}")
	public String deleteAirline(@PathVariable Integer id) {
		System.out.println(id);
		aRepo.deleteById(id);
		return "redirect:/list-airlines";

	}
	
	@RequestMapping(value = "/save-airline", method = RequestMethod.POST)
	public String saveAirlineSubmit(HttpServletRequest request, Model model, Airlines airline) {
		
		model.addAttribute("airline", aRepo.saveAndFlush(airline));
		return "redirect:/list-airlines";
	}
	
	@RequestMapping(value = "/create-airline")
	public String createAirline(Model model) {
		model.addAttribute("airline", new Airlines());
		return "createAirline";
	}

	@GetMapping(value = "/list-airlines")
	public String homeAirline(HttpServletRequest request, Model model) {
		int userId = 0;
		try {
			userId = Integer.valueOf(request.getSession().getAttribute("userId").toString());
			//System.out.println("id-search : " + userId);
		} catch (NullPointerException npe) {
			return "redirect:/login";
		}
		System.out.println("test");
		model.addAttribute("allAirline", aRepo.findAll());

		return "airline";
	}

}
