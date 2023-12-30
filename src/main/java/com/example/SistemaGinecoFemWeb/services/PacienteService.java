package com.example.SistemaGinecoFemWeb.services;

import java.util.List;

import com.example.SistemaGinecoFemWeb.entity.Paciente;

public interface PacienteService {
	public List<Paciente> findAll();
	public Paciente findById(int id);
	public Paciente findByDocumento(String documento);
	public List<Paciente> findByDocumentoContaining(String documento);
    public Paciente create(Paciente obj);
    public Paciente update(Paciente obj);
    public int delete(int id);
}
