package com.flightReservation.service;

import java.util.List;
import java.util.Optional;

import com.flightReservation.model.User;

public interface UserService {
	public List<User> findAll();
	public User findById(int user_id);
    public User findByUsername(String username);
    public void save (User user);
    public User edit (User user);
    public void deleteById (int id);
    public boolean login(String username,String password);
}
