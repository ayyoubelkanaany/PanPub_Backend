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
	@GetMapping("/Location/2/{id}")
	  public   ResponseEntity<List<Location>> findByEntrepriseId(@PathVariable long id) 
		  throws ResourceNotFoundException {
		List<Location> Location = repository.findByEntrepriseId(id)
				  .orElseThrow(() -> new ResourceNotFoundException("Entreprises not found for this id : "));
		   return ResponseEntity.ok().body(Location);
	  }
	
	@PostMapping("/Location")
	public Location createLocation(@Valid @RequestBody Location Location) {
		return repository.save(Location);
	}
	
	@DeleteMapping("/Location/1/{id}")
	public Map<String, Boolean> deleteLocationByEntrepriseId(@PathVariable(value = "id") Long EntrepriseId)
			throws ResourceNotFoundException {
		List<Location> Location = repository.findByEntrepriseId(EntrepriseId)
				.orElseThrow(() -> new ResourceNotFoundException("Location not found  id :: " + EntrepriseId));

		repository.deleteAll(Location);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
	@DeleteMapping("/Location/{id}")
	public Map<String, Boolean> deleteLocation(@PathVariable(value = "id") Long LocationId)
			throws ResourceNotFoundException {
		Location Location = repository.findById(LocationId)
				.orElseThrow(() -> new ResourceNotFoundException("Location not found  id :: " + LocationId));

		repository.delete(Location);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
	 
	  @DeleteMapping("/Location/delete")
	  public ResponseEntity<String> deleteAllLocation() {
	    System.out.println("Delete All Locations...");
	 
	    repository.deleteAll();
	 
	    return new ResponseEntity<>("All Locations have been deleted!", HttpStatus.OK);
	  }

	  @PutMapping("/Location/{id}")
	  public ResponseEntity<Location> updateLocation(@PathVariable("id") long id,@Valid @RequestBody Location Location) {
	    System.out.println("Update Location with ID = " + id + "...");
	 
	    Optional<Location> LocationInfo = repository.findById(id);
	 
	    if (LocationInfo.isPresent()) {
	    	Location location = LocationInfo.get();
	    	location.setDate_debut(Location.getDate_debut());
	    	location.setDate_fin(Location.getDate_fin());
	      return new ResponseEntity<>(repository.save(Location), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }@PostMapping("/new/Location")
		public void save(@RequestBody Location entity) {
			 repository.save(entity);
		}
		@PutMapping("update/Location")
		public int updateLocation(@RequestBody Location entity) {
			System.out.println(entity.getPanneau().getId());
			Optional<Location> Location = repository.findByPanneauId(entity.getPanneau().getId());
			Optional<Entreprise> entreprise = repositoryentreprise.findById(entity.getEntreprise().getId());
			Location.get().setEntreprise(entreprise.get());
			repository.save(Location.get());
			return 0;
		}
	
}
