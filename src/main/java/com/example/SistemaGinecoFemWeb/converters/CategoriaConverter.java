package com.example.SistemaGinecoFemWeb.converters;

import org.springframework.stereotype.Component;

import com.example.SistemaGinecoFemWeb.dto.CategoriaDTO;
import com.example.SistemaGinecoFemWeb.entity.Categoria;

@Component
public class CategoriaConverter extends AbstractConverter<Categoria,CategoriaDTO>{
	@Override
	public CategoriaDTO fromEntity(Categoria entity) {
		if(entity==null) return null;
		return CategoriaDTO.builder()
				.id(entity.getId())
				.nombre(entity.getNombre())
				.estado(entity.getEstado())
				.build();
	}

	@Override
	public Categoria fromDTO(CategoriaDTO dto) {
		if(dto==null) return null;
		return Categoria.builder()
				.id(dto.getId())
				.nombre(dto.getNombre())
				.estado(dto.getEstado())
				.build();
	}
}
