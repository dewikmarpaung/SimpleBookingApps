package com.flightReservation.model;

import javax.persistence.*;

@Entity
@Table(name = "city")
public class City {
	 @Id
	 @GeneratedValue(strategy= GenerationType.IDENTITY)
	 private int city_id;
	 private String name;
	 
	 public City () {
		 
	 }

	 
	    public City(int city_id, String name) {
		super();
		this.city_id = city_id;
		this.name = name;
	}


		public City(String name) {
	        this.name = name;
	    }

	    public int getCity_id() {
	        return city_id;
	    }

	    public void setCity_id(int city_id) {
	        this.city_id = city_id;
	    }

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	    
}
