package com.pabser.FlightReservation.service.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pabser.FlightReservation.model.SeatCategory;
import com.pabser.FlightReservation.model.Flight;
import com.pabser.FlightReservation.repository.SeatCategoryRepository;
import com.pabser.FlightReservation.service.SeatService;


@Service("SeatService")
public class SeatCategoryDao implements SeatService {

	@Autowired
	SeatCategoryRepository sRepo;

	@Override
	public SeatCategory addSeat(SeatCategory seat) {
		return sRepo.save(seat);
	}

	@Override
	public SeatCategory findSeatById(int id_Seat) {
		return sRepo.getOne(id_Seat);
	}

	@Override
	public List<SeatCategory> getAllSeat() {
		List<SeatCategory> sList = sRepo.findAll();
		if (sList != null) {
			return sList;
		} else {
			return null;
		}
	}

	@Override
	public SeatCategory deleteSeat(int id_Seat) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SeatCategory updateSeat(SeatCategory Seat) {
		// TODO Auto-generated method stub
		return null;
	}
	

	
}
