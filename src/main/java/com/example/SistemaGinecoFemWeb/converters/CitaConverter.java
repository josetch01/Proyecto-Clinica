package com.example.SistemaGinecoFemWeb.converters;

import java.sql.Date;

import org.springframework.stereotype.Component;

import com.example.SistemaGinecoFemWeb.dto.CitaDTO;
import com.example.SistemaGinecoFemWeb.entity.Cita;
@Component
public class CitaConverter extends AbstractConverter<Cita,CitaDTO>{
	@Override
	public CitaDTO fromEntity(Cita entity) {
		if(entity==null) return null;
		return CitaDTO.builder()
				.id(entity.getId())
				.historia(entity.getHistoria())
				.fecha_cita(entity.getFecha_cita())
				.paciente(entity.getPaciente())
				.medico(entity.getMedico())
				.estado(entity.getEstado())
				.producto(entity.getProducto())
				.build();
	}

	@Override
	public Cita fromDTO(CitaDTO dto) {
		if(dto==null) return null;
		return Cita.builder()
				.id(dto.getId())
				.historia(dto.getHistoria())
				.fecha_cita((Date) dto.getFecha_cita())
				.paciente(dto.getPaciente())
				.medico(dto.getMedico())
			    .estado(dto.getEstado())
				.producto(dto.getProducto())
				.build();
	}
}
