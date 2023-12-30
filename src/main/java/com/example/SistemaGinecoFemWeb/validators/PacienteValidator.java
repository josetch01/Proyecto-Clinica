package com.example.SistemaGinecoFemWeb.validators;

import com.example.SistemaGinecoFemWeb.entity.Paciente;
import com.example.SistemaGinecoFemWeb.exceptions.ValidateServiceException;

public class PacienteValidator {
	public static void save(Paciente paciente) {
		if(paciente.getNombre()==null || paciente.getNombre().trim().isEmpty()) {
			throw new ValidateServiceException("El nombre es requerido");
		}
		if(paciente.getNombre().length()>100) {
			throw new ValidateServiceException("El nombre es muy extenso");
		}
		if(paciente.getApellidos()==null || paciente.getApellidos().trim().isEmpty()) {
			throw new ValidateServiceException("El Apellido es requerido");
		}
		if(paciente.getApellidos().length()>100) {
			throw new ValidateServiceException("El Apellido es muy extenso");
		}
		if(paciente.getDocumento()==null || paciente.getDocumento().trim().isEmpty()) {
			throw new ValidateServiceException("El DNI es requerido");
		}
		if(paciente.getDocumento().length()>8) {
			throw new ValidateServiceException("El Documento solo debe tener 8 digitos");
		}
		if(paciente.getTelefono()==null || paciente.getTelefono().trim().isEmpty()) {
			throw new ValidateServiceException("El Telefono es requerido");
		}
		if(paciente.getTelefono().length()>9) {
			throw new ValidateServiceException("El Telefono solo debe tener 9 digitos");
		}
	}
}
