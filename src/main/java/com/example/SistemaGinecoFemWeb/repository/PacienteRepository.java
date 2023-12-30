package com.example.SistemaGinecoFemWeb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SistemaGinecoFemWeb.entity.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer>{
	public Paciente findByDocumento(String documento);
	public List<Paciente> findByDocumentoContaining(String documento);
}
