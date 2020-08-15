package com.example.PanPub.model.ws.provide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@GetMapping("/Panneau/1/{id}")
	public ResponseEntity<Panneau> getPanneauByEtat(@PathVariable(value = "etat") Long etat)
			throws ResourceNotFoundException {
		Panneau Etat = repository.findByEtat(etat)
				.orElseThrow(() -> new ResourceNotFoundException("Panneau not found for this id :: " + etat));
		return ResponseEntity.ok().body(Etat);
	}
	
	@PostMapping("/Panneau")
	public Panneau createPanneau(@Valid @RequestBody Panneau Panneau) {
		return repository.save(Panneau);
	}
	
	@DeleteMapping("/Panneau/{id}")
	public Map<String, Boolean> deletePanneau(@PathVariable(value = "id") Long PanneauId)
			throws ResourceNotFoundException {
		Panneau Panneau = repository.findById(PanneauId)
				.orElseThrow(() -> new ResourceNotFoundException("Panneau not found  id :: " + PanneauId));

		repository.delete(Panneau);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
	 
	  @DeleteMapping("/Panneau/delete")
	  public ResponseEntity<String> deleteAllPanneau() {
	    System.out.println("Delete All Panneaus...");
	 
	    repository.deleteAll();
	 
	    return new ResponseEntity<>("All Panneaus have been deleted!", HttpStatus.OK);
	  }

	  @PutMapping("/Panneau/{id}")
	  public ResponseEntity<Panneau> updatePanneau(@PathVariable("id") long id,@Valid @RequestBody Panneau Panneau) {
	    System.out.println("Update Panneau with ID = " + id + "...");
	 
	    Optional<Panneau> PanneauInfo = repository.findById(id);
	 
	    if (PanneauInfo.isPresent()) {
	    	Panneau panneau = PanneauInfo.get();
	    	panneau.setEtat(Panneau.getEtat());
	      return new ResponseEntity<>(repository.save(Panneau), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }@PostMapping("/new/Panneau")
		public Long save(@RequestBody Panneau entity) {
			 repository.save(entity);
	         return entity.getId();
		}

	
}
