package com.example.SistemaGinecoFemWeb.validators;

import com.example.SistemaGinecoFemWeb.entity.Preescripcion;
import com.example.SistemaGinecoFemWeb.exceptions.ValidateServiceException;

public class PreescripcionValidator {
	public static void save(Preescripcion preescripcion) {
		if(preescripcion.getNombre()==null || preescripcion.getNombre().trim().isEmpty()) {
			throw new ValidateServiceException("El nombre es requerido");
		}
		if(preescripcion.getNombre().length()>100) {
			throw new ValidateServiceException("El nombre es muy extenso");
		}
		if(preescripcion.getDescripcion()==null || preescripcion.getDescripcion().trim().isEmpty()) {
			throw new ValidateServiceException("La Descripcion es requerida");
		}
	}
}
