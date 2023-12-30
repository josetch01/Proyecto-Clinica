package com.example.SistemaGinecoFemWeb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SistemaGinecoFemWeb.entity.Especialidad;
@Repository
public interface EspecialidadRepository extends JpaRepository<Especialidad,Integer>{
	public Especialidad findByNombre(String nombre);
	public List<Especialidad> findByNombreContaining(String nombre);

}
