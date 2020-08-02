package com.example.PanPub.model.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PanPub.bean.Location;


@Repository
public interface LocationRepository extends JpaRepository<Location, Long>{
	Optional<Location> findByPanneauId(long type);
}
