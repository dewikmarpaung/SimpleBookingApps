package com.flightReservation.model;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	private int user_id;
    private String first_name;
    private String last_name;
    private String username;
    private String password;
    private String email;
    private int phone;
    
    public User() {
    	
    }
    
	public User(int user_id, String first_name, String last_name, String username, String password, String email,
			int phone) {
		super();
		this.user_id = user_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}
    
	
    
}
