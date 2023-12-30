package com.example.SistemaGinecoFemWeb.validators;

import com.example.SistemaGinecoFemWeb.entity.Medico;
import com.example.SistemaGinecoFemWeb.exceptions.ValidateServiceException;

public class MedicoValidator {
	public static void save(Medico medico) {
		if(medico.getNombre()==null || medico.getNombre().trim().isEmpty()) {
			throw new ValidateServiceException("El nombre es requerido");
		}
		if(medico.getNombre().length()>100) {
			throw new ValidateServiceException("El nombre es muy extenso");
		}
		if(medico.getApellidos()==null || medico.getApellidos().trim().isEmpty()) {
			throw new ValidateServiceException("El Apellido es requerido");
		}
		if(medico.getApellidos().length()>100) {
			throw new ValidateServiceException("El Apellido es muy extenso");
		}
		if(medico.getDocumento()==null || medico.getDocumento().trim().isEmpty()) {
			throw new ValidateServiceException("El DNI es requerido");
		}
		if(medico.getDocumento().length()>8) {
			throw new ValidateServiceException("El DNI solo debe tener 8 digitos");
		}
		if(medico.getTelefono()==null || medico.getTelefono().trim().isEmpty()) {
			throw new ValidateServiceException("El Telefono es requerido");
		}
		if(medico.getTelefono().length()>9) {
			throw new ValidateServiceException("El Telefono solo debe tener 9 digitos");
		}
	}
}
