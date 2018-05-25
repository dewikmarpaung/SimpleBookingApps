package com.pabser.FlightReservation.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "seatcategory")
public class SeatCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int seatId;
	private String seatName;
	
	
	
	public SeatCategory() {
		
	}
	public int getSeatId() {
		return seatId;
	}
	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}
	public String getSeatName() {
		return seatName;
	}
	public void setSeatName(String seatName) {
		this.seatName = seatName;
	}
	
	
}
