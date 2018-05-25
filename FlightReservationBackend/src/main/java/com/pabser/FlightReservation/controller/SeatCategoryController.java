package com.pabser.FlightReservation.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pabser.FlightReservation.model.SeatCategory;

import com.pabser.FlightReservation.repository.SeatCategoryRepository;

@Controller
public class SeatCategoryController {

	@Autowired
	SeatCategoryRepository sRepo;
	
	@RequestMapping(value = "/edit-seat/{id}")
	public String editAir(@PathVariable Integer id, Model model) {
		model.addAttribute("seat", sRepo.findById(id));
		return "editSeat";

	}
	
	@RequestMapping(value = "/delete-seat/{id}")
	public String deleteseat(@PathVariable Integer id) {
		System.out.println(id);
		sRepo.deleteById(id);
		return "redirect:/list-seats";

	}
	
	@RequestMapping(value = "/save-seat", method = RequestMethod.POST)
	public String saveseatSubmit(HttpServletRequest request, Model model, SeatCategory seat) {
		
		model.addAttribute("seat", sRepo.saveAndFlush(seat));
		return "redirect:/list-seats";
	}
	
	@RequestMapping(value = "/create-seat")
	public String createseat(Model model) {
		model.addAttribute("seat", new SeatCategory());
		return "createSeat";
	}

	@GetMapping(value = "/list-seats")
	public String homeseat(HttpServletRequest request, Model model) {
		int userId = 0;
		try {
			userId = Integer.valueOf(request.getSession().getAttribute("userId").toString());
			//System.out.println("id-search : " + userId);
		} catch (NullPointerException npe) {
			return "redirect:/login";
		}
		System.out.println("test");
		model.addAttribute("allSeat", sRepo.findAll());

		return "seat";
	}
}
