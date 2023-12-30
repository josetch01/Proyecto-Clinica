package com.example.SistemaGinecoFemWeb.converters;
import org.springframework.stereotype.Component;

import com.example.SistemaGinecoFemWeb.dto.ProductoDTO;
import com.example.SistemaGinecoFemWeb.entity.Producto;
@Component
public class ProductoConverter extends AbstractConverter<Producto,ProductoDTO>{

	@Override
	public ProductoDTO fromEntity(Producto entity) {
		if(entity==null) return null;
		return ProductoDTO.builder()
				.id(entity.getId())
				.nombre(entity.getNombre())
				.descripcion(entity.getDescripcion())
				.precio(entity.getPrecio())
				.estado(entity.getEstado())
				.categoria(entity.getCategoria())
				.build();
	}

	@Override
	public Producto fromDTO(ProductoDTO dto) {
		if(dto==null) return null;
		return Producto.builder()
				.id(dto.getId())
				.nombre(dto.getNombre())
				.descripcion(dto.getDescripcion())
				.precio(dto.getPrecio())
				.estado(dto.getEstado())
				.categoria(dto.getCategoria())
				.build();
	}
}
