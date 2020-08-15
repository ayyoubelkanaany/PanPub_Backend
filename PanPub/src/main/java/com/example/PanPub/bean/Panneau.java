package com.example.PanPub.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class Panneau {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long etat;
	private Double latitude;
	private Double longitude;
	@OneToOne(mappedBy = "panneau")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Location location;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getEtat() {
		return etat;
	}
	public void setEtat(Long etat) {
		this.etat = etat;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Location getlocation() {
		return location;
	}
	public void setlocation(Location location) {
		this.location = location;
	}
	public Panneau(Long id, Long etat, Double latitude, Double longitude, Location location) {
		super();
		this.id = id;
		this.etat = etat;
		this.latitude = latitude;
		this.longitude = longitude;
		this.location = location;
	}
	public Panneau(Long id) {
		super();
		this.id = id;
	}
	public Panneau() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((etat == null) ? 0 : etat.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Panneau other = (Panneau) obj;
		if (etat == null) {
			if (other.etat != null)
				return false;
		} else if (!etat.equals(other.etat))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (longitude == null) {
			if (other.longitude != null)
				return false;
		} else if (!longitude.equals(other.longitude))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Panneau [id=" + id + ", etat=" + etat + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", location=" + location + "]";
	}
	
}