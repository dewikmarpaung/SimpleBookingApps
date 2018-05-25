package com.pabser.FlightReservation.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pabser.FlightReservation.model.Payment;

public interface PaymentRepository extends  JpaRepository<Payment, Serializable>{

}
