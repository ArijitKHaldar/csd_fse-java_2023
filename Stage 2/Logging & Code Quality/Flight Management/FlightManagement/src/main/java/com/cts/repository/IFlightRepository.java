package com.cts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.entity.Flight;
import com.cts.entity.Passenger;

@Repository
public interface IFlightRepository extends JpaRepository<Flight, Integer>{
	public boolean addPassenger(Passenger p,Flight f);
	public boolean removePassenger(Passenger p,Flight f);
	public Optional<Flight> findByFid(String f);
	public Optional<Passenger> findByName(String n);
}
