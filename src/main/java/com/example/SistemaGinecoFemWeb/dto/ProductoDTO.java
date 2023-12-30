package com.example.SistemaGinecoFemWeb.dto;
import com.example.SistemaGinecoFemWeb.entity.Categoria;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDTO {
	private int id;
	private String nombre;
	private String descripcion;
	private double precio;
	private String estado;
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Categoria categoria;
}
