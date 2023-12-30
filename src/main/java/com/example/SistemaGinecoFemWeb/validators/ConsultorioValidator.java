package com.example.SistemaGinecoFemWeb.validators;

import com.example.SistemaGinecoFemWeb.entity.Consultorio;
import com.example.SistemaGinecoFemWeb.exceptions.ValidateServiceException;

public class ConsultorioValidator {
	public static void save(Consultorio consultorio) {
		if(consultorio.getNombre()==null || consultorio.getNombre().trim().isEmpty()) {
			throw new ValidateServiceException("El nombre es requerido");
		}
		if(consultorio.getNombre().length()>100) {
			throw new ValidateServiceException("El nombre es muy extenso");
		}
		if(consultorio.getCodigo()==null || consultorio.getCodigo().trim().isEmpty()) {
			throw new ValidateServiceException("El Codigo es requerido");
		}
	}
}
