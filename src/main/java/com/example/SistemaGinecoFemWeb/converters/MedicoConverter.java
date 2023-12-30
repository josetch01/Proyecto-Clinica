package com.example.SistemaGinecoFemWeb.converters;

import java.sql.Date;

import org.springframework.stereotype.Component;

import com.example.SistemaGinecoFemWeb.dto.MedicoDTO;
import com.example.SistemaGinecoFemWeb.entity.Medico;
@Component
public class MedicoConverter extends AbstractConverter<Medico,MedicoDTO>{
	@Override
	public MedicoDTO fromEntity(Medico entity) {
		if(entity==null) return null;
		return MedicoDTO.builder()
				.id(entity.getId())
				.nombre(entity.getNombre())
				.apellidos(entity.getApellidos())
				.nacimiento(entity.getNacimiento())
				.documento(entity.getDocumento())
				.telefono(entity.getTelefono())
				.consultorio(entity.getConsultorio())
				.especialidad(entity.getEspecialidad())
				.estado(entity.getEstado())
				.build();
	}

	@Override
	public Medico fromDTO(MedicoDTO dto) {
		if(dto==null) return null;
		return Medico.builder()
				.id(dto.getId())
				.nombre(dto.getNombre())
				.apellidos(dto.getApellidos())
				.nacimiento((Date) dto.getNacimiento())
				.documento(dto.getDocumento())
				.telefono(dto.getTelefono())
				.consultorio(dto.getConsultorio())
				.especialidad(dto.getEspecialidad())
				.estado(dto.getEstado())
				.build();
	}
}
