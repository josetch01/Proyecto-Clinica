package com.example.SistemaGinecoFemWeb.services;

import java.util.List;

import com.example.SistemaGinecoFemWeb.entity.Usuario;

public interface UsuarioService {
	public List<Usuario> findAll();
	public Usuario findById(int id);
	public Usuario findByEmail(String email);
    public Usuario create(Usuario obj);
    public Usuario update(Usuario obj);
    public int activate(int id);
    public int desactivate(int id);

}
