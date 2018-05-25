package com.pabser.FlightReservation.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pabser.FlightReservation.model.Airlines;
import com.pabser.FlightReservation.model.City;

@Repository
public interface AirlinesRepository extends  JpaRepository<Airlines, Serializable>{

}
