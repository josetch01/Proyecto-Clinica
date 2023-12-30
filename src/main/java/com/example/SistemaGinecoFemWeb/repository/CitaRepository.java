package com.example.SistemaGinecoFemWeb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SistemaGinecoFemWeb.entity.Cita;

@Repository
public interface CitaRepository extends JpaRepository<Cita,Integer>{
	public Cita findByHistoria(String historia);
	public List<Cita> findByHistoriaContaining(String historia);
}
