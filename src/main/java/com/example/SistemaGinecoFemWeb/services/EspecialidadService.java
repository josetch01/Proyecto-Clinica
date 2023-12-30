package com.example.SistemaGinecoFemWeb.services;

import java.util.List;

import com.example.SistemaGinecoFemWeb.entity.Especialidad;


public interface EspecialidadService {
	public List<Especialidad> findAll();
	public Especialidad findById(int id);
	public Especialidad findByNombre(String nombre);
	public List<Especialidad> findByNombreContaining(String nombre);
    public Especialidad create(Especialidad obj);
    public Especialidad update(Especialidad obj);
    public int delete(int id);
}
