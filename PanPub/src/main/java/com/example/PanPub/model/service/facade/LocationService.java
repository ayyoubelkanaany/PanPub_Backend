package com.example.PanPub.model.service.facade;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.PanPub.bean.Location;

@Service
public interface LocationService {
	Optional<Location> findByPanneauId(long panneau);
}
