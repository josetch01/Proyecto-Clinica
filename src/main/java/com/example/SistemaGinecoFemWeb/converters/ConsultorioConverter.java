package com.example.SistemaGinecoFemWeb.converters;

import org.springframework.stereotype.Component;

import com.example.SistemaGinecoFemWeb.dto.ConsultorioDTO;
import com.example.SistemaGinecoFemWeb.entity.Consultorio;

@Component
public class ConsultorioConverter extends AbstractConverter<Consultorio, ConsultorioDTO>{
	@Override
	public ConsultorioDTO fromEntity(Consultorio entity) {
		if(entity==null) return null;
		return ConsultorioDTO.builder()
				.id(entity.getId())
				.nombre(entity.getNombre())
				.codigo(entity.getCodigo())
				.build();
	}

	@Override
	public Consultorio fromDTO(ConsultorioDTO dto) {
		if(dto==null) return null;
		return Consultorio.builder()
				.id(dto.getId())
				.nombre(dto.getNombre())
				.codigo(dto.getCodigo())
				.build();
	}
}
