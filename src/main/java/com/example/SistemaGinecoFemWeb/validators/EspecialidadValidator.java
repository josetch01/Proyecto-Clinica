package com.example.SistemaGinecoFemWeb.validators;

import com.example.SistemaGinecoFemWeb.entity.Especialidad;
import com.example.SistemaGinecoFemWeb.exceptions.ValidateServiceException;

public class EspecialidadValidator {
	public static void save(Especialidad especialidad) {
		if(especialidad.getNombre()==null || especialidad.getNombre().trim().isEmpty()) {
			throw new ValidateServiceException("El nombre es requerido");
		}
		if(especialidad.getNombre().length()>100) {
			throw new ValidateServiceException("El nombre es muy extenso");
		}
	}
}
