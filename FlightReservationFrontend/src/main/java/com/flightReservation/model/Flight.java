package com.flightReservation.model;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "flight")
public class Flight implements Serializable{
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int flightId;
	private String airline;
	@NotNull
    private String departure_city;
    @NotNull
    private String destination_city;
    private int price;
    private String categorySeat;
    @Temporal(TemporalType.TIME)
    private Date departure_date;
    
    public Flight () {

    }

	public Flight(int flightId, String airline, @NotNull String departure_city, @NotNull String destination_city,
			int price, String categorySeat, Date departure_date) {
		super();
		this.flightId = flightId;
		this.airline = airline;
		this.departure_city = departure_city;
		this.destination_city = destination_city;
		this.price = price;
		this.categorySeat = categorySeat;
		this.departure_date = departure_date;
	}

	public int getFlightId() {
		return flightId;
	}

	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public String getDeparture_city() {
		return departure_city;
	}

	public void setDeparture_city(String departure_city) {
		this.departure_city = departure_city;
	}

	public String getDestination_city() {
		return destination_city;
	}

	public void setDestination_city(String destination_city) {
		this.destination_city = destination_city;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getCategorySeat() {
		return categorySeat;
	}

	public void setCategorySeat(String categorySeat) {
		this.categorySeat = categorySeat;
	}

	public Date getDeparture_date() {
		return departure_date;
	}

	public void setDeparture_date(Date departure_date) {
		this.departure_date = departure_date;
	}
    
    
    
	
    
}
