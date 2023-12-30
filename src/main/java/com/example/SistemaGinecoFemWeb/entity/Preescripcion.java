package com.example.SistemaGinecoFemWeb.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="preescripciones")
@EntityListeners(AuditingEntityListener.class)
public class Preescripcion {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	@Column(name="nombre", nullable=false, length =100, unique=true)
	private String nombre;	
	@Column(name="descripcion", nullable=false, length =250)
	private String descripcion;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="paciente_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Paciente paciente;
	@Embedded
	private TimeStamp timeStamp;
}
