package com.example.SistemaGinecoFemWeb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SistemaGinecoFemWeb.entity.EstadoCita;
@Repository
public interface EstadoCitaRepository  extends JpaRepository<EstadoCita,Integer>{
	public EstadoCita findByNombre(String nombre);
	public List<EstadoCita> findByNombreContaining(String nombre);
}
