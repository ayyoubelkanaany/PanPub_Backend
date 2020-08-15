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
	
	@PostMapping("/Entreprise")
	public Entreprise createEntreprise(@Valid @RequestBody Entreprise Entreprise) {
		return repository.save(Entreprise);
	}
	
	@DeleteMapping("/Entreprise/{id}")
	public Map<String, Boolean> deleteEntreprise(@PathVariable(value = "id") Long EntrepriseId)
			throws ResourceNotFoundException {
		Entreprise Entreprise = repository.findById(EntrepriseId)
				.orElseThrow(() -> new ResourceNotFoundException("Entreprise not found  id :: " + EntrepriseId));

		repository.delete(Entreprise);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
	 
	  @DeleteMapping("/Entreprise/delete")
	  public ResponseEntity<String> deleteAllEntreprise() {
	    System.out.println("Delete All Entreprises...");
	 
	    repository.deleteAll();
	 
	    return new ResponseEntity<>("All Entreprises have been deleted!", HttpStatus.OK);
	  }

	  @PutMapping("/Entreprise/{id}")
	  public ResponseEntity<Entreprise> updateEntreprise(@PathVariable("id") long id,@Valid @RequestBody Entreprise Entreprise) {
	    System.out.println("Update Entreprise with ID = " + id + "...");
	 
	    Optional<Entreprise> EntrepriseInfo = repository.findById(id);
	 
	    if (EntrepriseInfo.isPresent()) {
	    	Entreprise entreprise = EntrepriseInfo.get();
	    	entreprise.setNom(Entreprise.getNom());
	    	entreprise.setPatente(Entreprise.getPatente());
	      return new ResponseEntity<>(repository.save(Entreprise), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	
}
