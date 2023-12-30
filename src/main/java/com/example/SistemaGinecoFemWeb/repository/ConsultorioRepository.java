package com.example.SistemaGinecoFemWeb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SistemaGinecoFemWeb.entity.Consultorio;
@Repository
public interface ConsultorioRepository extends JpaRepository<Consultorio,Integer>{
	public Consultorio findByNombre(String nombre);
	public List<Consultorio> findByNombreContaining(String nombre);
}
