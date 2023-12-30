package com.example.SistemaGinecoFemWeb.converters;

import org.springframework.stereotype.Component;

import com.example.SistemaGinecoFemWeb.dto.UsuarioDTO;
import com.example.SistemaGinecoFemWeb.entity.Usuario;

@Component
public class UsuarioConverter extends AbstractConverter<Usuario,UsuarioDTO>{

	@Override
	public UsuarioDTO fromEntity(Usuario entity) {
		if(entity==null) return null;
		return UsuarioDTO.builder()
				.id(entity.getId())
				.nombre(entity.getNombre())
				.email(entity.getEmail())
				.activo(entity.getActivo())
				.build();
	}

	@Override
	public Usuario fromDTO(UsuarioDTO dto) {
		if(dto==null) return null;
				
				return Usuario.builder()
						.id(dto.getId())
						.nombre(dto.getNombre())
						.email(dto.getEmail())
						.build();
	}
}

