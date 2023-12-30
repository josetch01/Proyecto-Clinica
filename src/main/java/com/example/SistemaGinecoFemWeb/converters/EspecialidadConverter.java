package com.example.SistemaGinecoFemWeb.converters;

import org.springframework.stereotype.Component;

import com.example.SistemaGinecoFemWeb.dto.EspecialidadDTO;
import com.example.SistemaGinecoFemWeb.entity.Especialidad;
@Component
public class EspecialidadConverter extends  AbstractConverter<Especialidad,EspecialidadDTO>{
	@Override
	public EspecialidadDTO fromEntity(Especialidad entity) {
		if(entity==null) return null;
		return EspecialidadDTO.builder()
				.id(entity.getId())
				.nombre(entity.getNombre())
				.build();
	}

	@Override
	public Especialidad fromDTO(EspecialidadDTO dto) {
		if(dto==null) return null;
		return Especialidad.builder()
				.id(dto.getId())
				.nombre(dto.getNombre())
				.build();
	}
}
