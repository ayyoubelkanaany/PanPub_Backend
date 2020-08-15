package com.example.PanPub.model.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PanPub.bean.Panneau;
import com.example.PanPub.model.dao.PanneauRepository;
import com.example.PanPub.model.service.facade.PanneauService;

@Service
public class PanneauImpl implements PanneauService{
	
	@Autowired
	private  PanneauRepository panneauRepository;
	
	@Override
	public Optional<Panneau> findByEtat(long etat) {
		
		return panneauRepository.findByEtat(etat);
	}

}
