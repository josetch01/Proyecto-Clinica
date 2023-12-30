package com.example.SistemaGinecoFemWeb.dto;

import java.util.Date;

import com.example.SistemaGinecoFemWeb.entity.EstadoCita;
import com.example.SistemaGinecoFemWeb.entity.Medico;
import com.example.SistemaGinecoFemWeb.entity.Paciente;
import com.example.SistemaGinecoFemWeb.entity.Producto;
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
public class CitaDTO {
	private int id;
	private String historia;
	private Date fecha_cita;
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Paciente paciente;
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Medico medico;
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private EstadoCita estado;
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Producto producto;
}
