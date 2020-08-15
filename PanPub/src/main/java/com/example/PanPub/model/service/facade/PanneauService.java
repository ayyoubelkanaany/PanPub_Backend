package com.example.PanPub.model.service.facade;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.PanPub.bean.Panneau;

@Service
public interface PanneauService {
	Optional<Panneau> findByEtat(long etat);
}
