package com.example.SistemaGinecoFemWeb.validators;

import com.example.SistemaGinecoFemWeb.entity.Usuario;
import com.example.SistemaGinecoFemWeb.exceptions.ValidateServiceException;

public class UsuarioValidator {
	public static void save(Usuario usuario) {
	if(usuario.getEmail()==null || usuario.getEmail().trim().isEmpty()) {
		throw new ValidateServiceException("El Email es requerido");
	}
	if(usuario.getEmail().length()>100) {
		throw new ValidateServiceException("El Email es muy extenso");
	}
	if(usuario.getNombre()==null || usuario.getNombre().trim().isEmpty()) {
		throw new ValidateServiceException("El Nombre es requerido");
	}
	if(usuario.getPassword()==null || usuario.getPassword().trim().isEmpty()) {
		throw new ValidateServiceException("La Contraseña es requerida");
	}
}
}
