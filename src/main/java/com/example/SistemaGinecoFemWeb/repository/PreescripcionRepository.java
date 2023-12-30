package com.example.SistemaGinecoFemWeb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SistemaGinecoFemWeb.entity.Preescripcion;

public interface PreescripcionRepository extends JpaRepository<Preescripcion,Integer>{
	public Preescripcion findByNombre(String nombre);
	public List<Preescripcion> findByNombreContaining(String nombre);
}
