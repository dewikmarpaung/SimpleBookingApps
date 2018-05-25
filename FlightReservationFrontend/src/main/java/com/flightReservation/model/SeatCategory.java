package com.flightReservation.model;

import javax.persistence.*;

@Entity
@Table(name = "seatcategory")
public class SeatCategory {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int seatId;
	private String seatName;
	
	public SeatCategory() {
		
	}

	public SeatCategory(int seatId, String seatName) {
		super();
		this.seatId = seatId;
		this.seatName = seatName;
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
