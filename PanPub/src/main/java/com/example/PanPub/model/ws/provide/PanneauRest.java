package com.example.PanPub.model.ws.provide;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.PanPub.Exception.ResourceNotFoundException;
import com.example.PanPub.bean.Panneau;
import com.example.PanPub.model.dao.PanneauRepository;



@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class PanneauRest {
	
	@Autowired
	PanneauRepository repository;
	
	 @GetMapping("/Panneau")
	  public List<Panneau> getAllPanneau() {
	    System.out.println("Get all Panneaux...");
	    List<Panneau> Panneau = new ArrayList<>();
	    repository.findAll().forEach(Panneau::add);
	 
	    return Panneau;
	  }
	
	@GetMapping("/Panneau/{id}")
	public ResponseEntity<Panneau> getPanneauById(@PathVariable(value = "id") Long Id)
			throws ResourceNotFoundException {
		Panneau Panneau = repository.findById(Id)
				.orElseThrow(() -> new ResourceNotFoundException("Panneau not found for this id :: " + Id));
		return ResponseEntity.ok().body(Panneau);
	}
	
}
