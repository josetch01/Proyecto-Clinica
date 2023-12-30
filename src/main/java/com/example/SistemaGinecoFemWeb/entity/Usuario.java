package com.example.SistemaGinecoFemWeb.entity;



import javax.persistence.*;

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
@Table(name="usuarios")
@EntityListeners(AuditingEntityListener.class)
public class Usuario {
	@Id
	@Column(name="id", columnDefinition="int")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="nombre", nullable=false, length =100, unique=true)
	private String nombre;
	@Column(name="activo",nullable=false)
	private Boolean activo;
	@Column(name="email", nullable=false, length =100, unique=true)
	private String email;
	@Column(name="password", nullable=false, length =100)
	private String password;
	/*@ManyToOne
	@JoinColumn(name="rolId")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Rol rol;*/
	@Embedded
	private TimeStamp timeStamp;
	
}
