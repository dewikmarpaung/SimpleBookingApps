package com.flightReservation.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flightReservation.model.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Serializable>{

}
