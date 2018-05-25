package com.flightReservation.model;

import javax.persistence.*;

@Entity
@Table(name = "payment")
public class Payment {
	 @Id
	 @GeneratedValue(strategy= GenerationType.IDENTITY)
	 private int payment_id;
	 private int totalPrice;
	 private String token;
	 private int user_id;
	 private int booking_id;
	 
	 public Payment () {
		 
	 }
	 
	public Payment(int payment_id, int totalPrice, String token, int user_id, int booking_id) {
		super();
		this.payment_id = payment_id;
		this.totalPrice = totalPrice;
		this.token = token;
		this.user_id = user_id;
		this.booking_id = booking_id;
	}

	public int getPayment_id() {
		return payment_id;
	}

	public void setPayment_id(int payment_id) {
		this.payment_id = payment_id;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getBooking_id() {
		return booking_id;
	}

	public void setBooking_id(int booking_id) {
		this.booking_id = booking_id;
	}
	 

}
