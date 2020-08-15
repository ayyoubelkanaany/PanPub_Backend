package com.example.PanPub.model.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PanPub.bean.Panneau;

@Repository
public interface PanneauRepository extends JpaRepository<Panneau, Long>{
	Optional<Panneau> findByEtat(long etat);
}
