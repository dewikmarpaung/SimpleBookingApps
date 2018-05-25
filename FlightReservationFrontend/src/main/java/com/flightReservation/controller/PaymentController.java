package com.flightReservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.flightReservation.model.Payment;
import com.flightReservation.repository.PaymentRepository;
import com.flightReservation.service.PaymentService;
import com.flightReservation.tools.View;


@Controller
@RestController
public class PaymentController {
	@Autowired
	PaymentService paymentService;
	
	@RequestMapping(value = "api-payment", method=RequestMethod.GET)
	@ResponseBody
	public List<Payment> getListAllPayment() {
		List<Payment> listPayment = paymentService.findAll();
		return listPayment;
	}
	
	@JsonView(View.Public.class)
	@RequestMapping(value = "api-payment/{id}", method = RequestMethod.GET)
	public ResponseEntity<Payment> getPaymentById(@PathVariable Integer id, @ModelAttribute Payment payment) {
		Payment paymentTemp = paymentService.findById(id);
		if(paymentTemp == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Payment>(paymentTemp,HttpStatus.OK);
	}
}
