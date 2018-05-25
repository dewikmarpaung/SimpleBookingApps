package com.pabser.FlightReservation.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pabser.FlightReservation.model.*;

@Repository
public interface UserRepository extends JpaRepository<User, Serializable> {
	
}
