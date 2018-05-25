package com.pabser.FlightReservation.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "airlines")
public class Airlines {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int airlines_id;
	private String name;
	
	
	public Airlines() {
		
	}
	public int getAirlines_id() {
		return airlines_id;
	}
	public void setAirlines_id(int airlines_id) {
		this.airlines_id = airlines_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
