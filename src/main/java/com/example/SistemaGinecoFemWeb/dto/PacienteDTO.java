package com.example.SistemaGinecoFemWeb.dto;

import java.util.Date;

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
public class PacienteDTO {
	private int id;
	private String nombre;
	private String apellidos;
	private Date nacimiento;
	private String documento;
	private String telefono;
	private String direccion;
	private String ciudad;
	private String estado;
}
