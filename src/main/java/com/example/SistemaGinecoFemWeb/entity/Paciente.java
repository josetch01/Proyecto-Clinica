package com.example.SistemaGinecoFemWeb.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name="pacientes")
@EntityListeners(AuditingEntityListener.class)
public class Paciente {
	@Id
	@Column(name="id", columnDefinition="int")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="nombre", nullable=false, length =100, unique=false)
	private String nombre;
	@Column(name="apellidos", nullable=false, length =100, unique=false)
	private String apellidos;
	@Column(name="nacimiento", nullable=false, columnDefinition="date", unique=false)
	private Date nacimiento;
	@Column(name="documento", nullable=false, length =8, unique=true)
	private String documento;
	@Column(name="telefono", nullable=false, length =9, unique=true)
	private String telefono;
	@Column(name="direccion", nullable=true, length =100, unique=false)
	private String direccion;
	@Column(name="ciudad", nullable=true, length =100, unique=false)
	private String ciudad;
	@Column(name="estado",nullable=false)
	private String estado;
	@Embedded
	private TimeStamp timeStamp;

}
