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
import com.example.PanPub.bean.Entreprise;
import com.example.PanPub.model.dao.EntrepriseRepository;



@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class EntrepriseRest {
	
	@Autowired
	EntrepriseRepository repository;
	
	 @GetMapping("/Entreprise")
	  public List<Entreprise> getAllEntreprise() {
	    System.out.println("Get all Entrepriss...");
	    List<Entreprise> Entreprise = new ArrayList<>();
	    repository.findAll().forEach(Entreprise::add);
	 
	    return Entreprise;
	  }
	
	@GetMapping("/Entreprise/{id}")
	public ResponseEntity<Entreprise> getEntrepriseById(@PathVariable(value = "id") Long Id)
			throws ResourceNotFoundException {
		Entreprise Entreprise = repository.findById(Id)
				.orElseThrow(() -> new ResourceNotFoundException("Entreprise not found for this id :: " + Id));
		return ResponseEntity.ok().body(Entreprise);
	}
	
}
