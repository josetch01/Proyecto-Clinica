package com.example.SistemaGinecoFemWeb.services;

import java.util.List;

import com.example.SistemaGinecoFemWeb.entity.Consultorio;

public interface ConsultorioService {
	public List<Consultorio> findAll();
	public Consultorio findById(int id);
	public Consultorio findByNombre(String nombre);
	public List<Consultorio> findByNombreContaining(String nombre);
    public Consultorio create(Consultorio obj);
    public Consultorio update(Consultorio obj);
    public int delete(int id);
}
