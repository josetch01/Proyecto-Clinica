package com.example.SistemaGinecoFemWeb.dto;

import java.util.Date;

import com.example.SistemaGinecoFemWeb.entity.Consultorio;
import com.example.SistemaGinecoFemWeb.entity.Especialidad;
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
public class MedicoDTO {
	private int id;
	private String nombre;
	private String apellidos;
	private String documento;
	private Date nacimiento;
	private String telefono;
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Consultorio consultorio;
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Especialidad especialidad;
	private String estado;
	
}
