package com.pabser.FlightReservation.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonView;
import com.pabser.FlightReservation.tools.View;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "flight")
@XmlRootElement
@JsonRootName(value = "")
public class Flight implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column
    @JsonView(View.Public.class)
    private int flightId;
    @Column
    @JsonView(View.Public.class)
    private int price;
    @Column
    @JsonView(View.Public.class)
    private String departure_city;
    @Column
    @JsonView(View.Public.class)
    private String destination_city;
    @Column
    @JsonView(View.Public.class)
    private String categorySeat;
    @Column
    @JsonView(View.Public.class)
    private Date departure_date;
    @Column
    @JsonView(View.Public.class)
    private String airline;

    
	public Flight() {
		
	}
	

	public Flight(int flightId, int price, String departure_city, String destination_city, String categorySeat,
			Date departure_date, String airline) {
		super();
		this.flightId = flightId;
		this.price = price;
		this.departure_city = departure_city;
		this.destination_city = destination_city;
		this.categorySeat = categorySeat;
		this.departure_date = departure_date;
		this.airline = airline;
	}

	public int getFlightId() {
		return flightId;
	}
	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	public Date getDeparture_date() {
		return departure_date;
	}
	public void setDeparture_date(Date departure_date) {
		this.departure_date = departure_date;
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
	public String getAirline() {
		return airline;
	}
	public void setAirline(String airline) {
		this.airline = airline;
	}
	public String getCategorySeat() {
		return categorySeat;
	}
	public void setCategorySeat(String categorySeat) {
		this.categorySeat = categorySeat;
	}
	


}
