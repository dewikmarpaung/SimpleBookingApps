package com.flightReservation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightReservation.model.Payment;
import com.flightReservation.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService{

	@Autowired
	PaymentRepository paymentRepository;
	
	@Override
	public List<Payment> findAll() {
		// TODO Auto-generated method stub
		return paymentRepository.findAll();
	}

	@Override
	public Payment findById(int payment_id) {
		// TODO Auto-generated method stub
		return paymentRepository.getOne(payment_id);
	}

	@Override
	public void save(Payment payment) {
		// TODO Auto-generated method stub
		paymentRepository.save(payment);
	}

}
