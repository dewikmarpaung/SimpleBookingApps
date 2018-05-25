package com.pabser.FlightReservation.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonView;
import com.pabser.FlightReservation.tools.View;

@Entity
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column
    @JsonView(View.Public.class)
    private int city_id;
    @Column
    @JsonView(View.Public.class)
    private String name;

    public City() {
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
