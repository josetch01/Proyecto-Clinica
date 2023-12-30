package com.example.SistemaGinecoFemWeb.entity;

import java.sql.Date;

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
@Table(name="citas")
@EntityListeners(AuditingEntityListener.class)
public class Cita {
	@Id
	@Column(name="id", columnDefinition="int")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="historia", nullable=false, length =100, unique=true)
	private String historia;
	@Column(name="fecha_cita",columnDefinition="date", nullable=false)
	private Date fecha_cita;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="paciente_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Paciente paciente;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="medico_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Medico medico;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="estado_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private EstadoCita estado;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="servicio_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Producto producto;
	@Embedded
	private TimeStamp timeStamp;
}
