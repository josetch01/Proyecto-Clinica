package com.example.SistemaGinecoFemWeb.validators;

import com.example.SistemaGinecoFemWeb.entity.Cita;
import com.example.SistemaGinecoFemWeb.exceptions.ValidateServiceException;

public class CitaValidator {
	public static void save(Cita cita) {
		if(cita.getHistoria()==null || cita.getHistoria().trim().isEmpty()) {
			throw new ValidateServiceException("La Historia CLinica es requerida");
		}
		if(cita.getHistoria().length()>20) {
			throw new ValidateServiceException("La Historia Clinica es muy extensa");
		}
	}
}
