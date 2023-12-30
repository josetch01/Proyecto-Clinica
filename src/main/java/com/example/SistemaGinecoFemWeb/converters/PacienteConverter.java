package com.example.SistemaGinecoFemWeb.converters;

import java.sql.Date;

import org.springframework.stereotype.Component;

import com.example.SistemaGinecoFemWeb.dto.PacienteDTO;
import com.example.SistemaGinecoFemWeb.entity.Paciente;
@Component
public class PacienteConverter extends AbstractConverter<Paciente,PacienteDTO>{

	@Override
	public PacienteDTO fromEntity(Paciente entity) {
		if(entity==null) return null;
		return PacienteDTO.builder()
				.id(entity.getId())
				.nombre(entity.getNombre())
				.apellidos(entity.getApellidos())
				.nacimiento(entity.getNacimiento())
				.documento(entity.getDocumento())
				.telefono(entity.getTelefono())
				.direccion(entity.getDireccion())
				.ciudad(entity.getCiudad())
				.estado(entity.getEstado())
				.build();
	}

	@Override
	public Paciente fromDTO(PacienteDTO dto) {
		if(dto==null) return null;
		return Paciente.builder()
				.id(dto.getId())
				.nombre(dto.getNombre())
				.apellidos(dto.getApellidos())
				.nacimiento((Date) dto.getNacimiento())
				.documento(dto.getDocumento())
				.telefono(dto.getTelefono())
				.direccion(dto.getDireccion())
				.ciudad(dto.getCiudad())
				.estado(dto.getEstado())
				.build();
	}
}


