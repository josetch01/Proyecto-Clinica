package com.example.SistemaGinecoFemWeb.converters;

import org.springframework.stereotype.Component;

import com.example.SistemaGinecoFemWeb.dto.EstadoCitaDTO;
import com.example.SistemaGinecoFemWeb.entity.EstadoCita;
@Component
public class EstadoCitaConverter extends AbstractConverter<EstadoCita,EstadoCitaDTO>{
	@Override
	public EstadoCitaDTO fromEntity(EstadoCita entity) {
		if(entity==null) return null;
		return EstadoCitaDTO.builder()
				.id(entity.getId())
				.nombre(entity.getNombre())
				.build();
	}

	@Override
	public EstadoCita fromDTO(EstadoCitaDTO dto) {
		if(dto==null) return null;
		return EstadoCita.builder()
				.id(dto.getId())
				.nombre(dto.getNombre())
				.build();
	}
}
