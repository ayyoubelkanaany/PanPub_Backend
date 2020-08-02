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
import com.example.PanPub.bean.Location;
import com.example.PanPub.model.dao.LocationRepository;




@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class LocationRest {
	
	@Autowired
	LocationRepository repository;
	
	 @GetMapping("/Location")
	  public List<Location> getAllLocation() {
	    System.out.println("Get all Locations...");
	    List<Location> Location = new ArrayList<>();
	    repository.findAll().forEach(Location::add);
	 
	    return Location;
	  }
	
	@GetMapping("/Location/{id}")
	public ResponseEntity<Location> getLocationById(@PathVariable(value = "id") Long Id)
			throws ResourceNotFoundException {
		Location Location = repository.findById(Id)
				.orElseThrow(() -> new ResourceNotFoundException("Location not found for this id :: " + Id));
		return ResponseEntity.ok().body(Location);
	}
	@GetMapping("/Location/1/{id}")
	  public   ResponseEntity<Location> findByPanneauId(@PathVariable long id) 
		  throws ResourceNotFoundException {
		Location Location = repository.findByPanneauId(id)
				  .orElseThrow(() -> new ResourceNotFoundException("Location not found for this id : "));
		   return ResponseEntity.ok().body(Location);
	  }
	
}
