package com.example.SistemaGinecoFemWeb.services;

import java.util.List;

import com.example.SistemaGinecoFemWeb.entity.Medico;

public interface MedicoService {
	public List<Medico> findAll();
	public Medico findById(int id);
	public Medico findByDocumento(String documento);
	public List<Medico> findByDocumentoContaining(String documento);
    public Medico create(Medico obj);
    public Medico update(Medico obj);
    public int delete(int id);
}
