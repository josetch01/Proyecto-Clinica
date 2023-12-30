package com.example.SistemaGinecoFemWeb.services;

import java.util.List;

import com.example.SistemaGinecoFemWeb.entity.Preescripcion;

public interface PreescripcionService {
	public List<Preescripcion> findAll();
	public Preescripcion findById(int id);
	public Preescripcion findByNombre(String nombre);
	public List<Preescripcion> findByNombreContaining(String nombre);
    public Preescripcion create(Preescripcion obj);
    public Preescripcion update(Preescripcion obj);
    public int delete(int id);
}
