package com.example.SistemaGinecoFemWeb.dto;

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
public class ConsultorioDTO {
	private int id;
	private String nombre;
	private String codigo;
}
