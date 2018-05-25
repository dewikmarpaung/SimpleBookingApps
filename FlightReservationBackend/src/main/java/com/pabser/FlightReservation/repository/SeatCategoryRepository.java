package com.pabser.FlightReservation.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pabser.FlightReservation.model.SeatCategory;

public interface SeatCategoryRepository extends  JpaRepository<SeatCategory, Serializable>{

}
