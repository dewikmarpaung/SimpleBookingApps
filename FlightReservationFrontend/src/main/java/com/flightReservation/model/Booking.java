package com.flightReservation.model;

import javax.persistence.*;

@Entity
@Table(name = "booking")
public class Booking {
	 @Id
	 @GeneratedValue(strategy= GenerationType.IDENTITY)
	 private int booking_id;
	 private String full_name;
	 private int phone;
	 private String email;
	 private int flight_id;
	 private int user_id;
	 private String listPassengerName;
	 
	 public Booking() {
		 
	 }

	public Booking(int booking_id, String full_name, int phone, String email, int flight_id, int user_id,
			String listPassengerName) {
		super();
		this.booking_id = booking_id;
		this.full_name = full_name;
		this.phone = phone;
		this.email = email;
		this.flight_id = flight_id;
		this.user_id = user_id;
		this.listPassengerName = listPassengerName;
	}

	public int getBooking_id() {
		return booking_id;
	}

	public void setBooking_id(int booking_id) {
		this.booking_id = booking_id;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getFlight_id() {
		return flight_id;
	}

	public void setFlight_id(int flight_id) {
		this.flight_id = flight_id;
	}

	public int getUSer_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getListPassengerName() {
		return listPassengerName;
	}

	public void setListPassengerName(String listPassengerName) {
		this.listPassengerName = listPassengerName;
	}
	
	
	 
}
