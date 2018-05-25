package com.pabser.FlightReservation.service.dao;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pabser.FlightReservation.model.User;
import com.pabser.FlightReservation.repository.UserRepository;
import com.pabser.FlightReservation.service.*;

@Service
public class UserDao implements UserService{
	@Autowired
    private UserRepository userRepository;

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
    public User findByUsername(String username) {
        try{
            List<User> listUser = findAll();
            //System.out.print(listUser.size());
            for (User user : listUser) {
                if (user.getUsername().equalsIgnoreCase(username)) {
                    return user;
                }
            }
        }
        catch (Exception e){
            return null;
        }
        return null;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User edit(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean login(String username, String password) {
        List<User> listUser = findAll();
        for ( User user : listUser){
            //System.out.println(user.getUsername());
            if (user.getUsername().equalsIgnoreCase(username) && user.getPassword().equalsIgnoreCase(password)) {
                return true;
            }
        }
        return false;
    }

	@Override
	public User findById(int user_id) {
		return userRepository.getOne(user_id);
	}

}
