package com.flightReservation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightReservation.model.SeatCategory;
import com.flightReservation.repository.SeatCategoryRepository;

@Service
public class SeatCategoryServiceImpl implements SeatCategoryService{
	@Autowired
	SeatCategoryRepository seatCategoryRepository;

	@Override
	public List<SeatCategory> findAll() {
		// TODO Auto-generated method stub
		return seatCategoryRepository.findAll();
	}

	@Override
	public SeatCategory findById(int seatId) {
		// TODO Auto-generated method stub
		return seatCategoryRepository.getOne(seatId);
	}

}
