package com.example.SistemaGinecoFemWeb.entity;

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
@Getter
@Setter
@Builder
@Entity
@Table(name="estados")
@EntityListeners(AuditingEntityListener.class)
public class EstadoCita {
	@Id
	@Column(name="id", columnDefinition="int")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="nombre", nullable=false, length =100, unique=true)
	private String nombre;
	@Column(name="estado",nullable=false, columnDefinition="bit default 1")
	private int estado;
	@Embedded
	private TimeStamp timeStamp;

}
