package com.example.PanPub.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PanPub.bean.Entreprise;


@Repository
public interface EntrepriseRepository extends JpaRepository<Entreprise, Long>{

}
