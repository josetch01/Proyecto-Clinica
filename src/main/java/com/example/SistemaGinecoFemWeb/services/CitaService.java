package com.example.SistemaGinecoFemWeb.services;

import java.util.List;

import com.example.SistemaGinecoFemWeb.entity.Cita;

public interface CitaService {
	public List<Cita> findAll();
	public Cita findById(int id);
	public Cita findByHistoria(String historia);
	public List<Cita> findByHistoriaContaining(String historia);
    public Cita create(Cita obj);
    public Cita update(Cita obj);
    public int delete(int id);
}
