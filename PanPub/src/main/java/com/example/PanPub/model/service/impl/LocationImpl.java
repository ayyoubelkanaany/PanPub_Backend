package com.example.PanPub.model.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PanPub.bean.Location;
import com.example.PanPub.model.dao.LocationRepository;
import com.example.PanPub.model.service.facade.LocationService;


@Service
public class LocationImpl implements LocationService{
	@Autowired
	private  LocationRepository locationRepository;

	@Override
	public Optional<Location> findByPanneauId(long type) {
		
		return locationRepository.findByPanneauId(type);
	}

	@Override
	public Optional<List<Location>> findByEntrepriseId(long entreprise) {
		
		return locationRepository.findByEntrepriseId(entreprise);
	}
	
}
