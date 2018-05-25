package com.flightReservation.service;

import java.util.List;

import com.flightReservation.model.Payment;

public interface PaymentService {
	public List<Payment> findAll();
    public Payment findById(int payment_id);
    public void save (Payment payment);
}
