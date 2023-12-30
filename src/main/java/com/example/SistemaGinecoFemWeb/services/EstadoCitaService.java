package com.example.SistemaGinecoFemWeb.services;

import com.example.SistemaGinecoFemWeb.entity.EstadoCita;

import java.util.List;

public interface EstadoCitaService {
	public List<EstadoCita> findAll();
	public EstadoCita findById(int id);
	public EstadoCita findByNombre(String nombre);
	public List<EstadoCita> findByNombreContaining(String nombre);
    public EstadoCita create(EstadoCita obj);
    public EstadoCita update(EstadoCita obj);
    public int delete(int id);
}
