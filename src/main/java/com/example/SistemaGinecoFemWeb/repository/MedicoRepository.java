package com.example.SistemaGinecoFemWeb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SistemaGinecoFemWeb.entity.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico,Integer>{
	public Medico findByDocumento(String documento);
	public List<Medico> findByDocumentoContaining(String documento);
}
