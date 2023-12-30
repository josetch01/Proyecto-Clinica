package com.example.SistemaGinecoFemWeb.validators;

import com.example.SistemaGinecoFemWeb.entity.EstadoCita;
import com.example.SistemaGinecoFemWeb.exceptions.ValidateServiceException;

public class EstadoValidator {
	public static void save(EstadoCita estados) {
		if(estados.getNombre()==null || estados.getNombre().trim().isEmpty()) {
			throw new ValidateServiceException("El nombre es requerido");
		}
		if(estados.getNombre().length()>100) {
			throw new ValidateServiceException("El nombre es muy extenso");
		}

	}
}
