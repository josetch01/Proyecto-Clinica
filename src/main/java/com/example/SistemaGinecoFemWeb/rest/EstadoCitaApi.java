package com.example.SistemaGinecoFemWeb.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SistemaGinecoFemWeb.converters.EstadoCitaConverter;
import com.example.SistemaGinecoFemWeb.dto.EstadoCitaDTO;
import com.example.SistemaGinecoFemWeb.entity.EstadoCita;
import com.example.SistemaGinecoFemWeb.services.EstadoCitaService;



@RestController
@RequestMapping("/api/estados")
public class EstadoCitaApi {
	@Autowired
	private EstadoCitaService service;
	
	@Autowired
	private EstadoCitaConverter converterEstado;
	@GetMapping()
	public ResponseEntity<List<EstadoCitaDTO>> getAll(){
		List<EstadoCita> estadocitas= service.findAll();
		List<EstadoCitaDTO> estadocitaDTO = converterEstado.fromEntity(estadocitas);
		return ResponseEntity.status(HttpStatus.OK).body(estadocitaDTO);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<EstadoCitaDTO> getById(@PathVariable("id") int id) {
		EstadoCita estadocita = service.findById(id);
		EstadoCitaDTO estadocitaDTO=converterEstado.fromEntity(estadocita);
		return ResponseEntity.status(HttpStatus.OK).body(estadocitaDTO);
	}
	
	@PostMapping
	public ResponseEntity<EstadoCita> create(@RequestBody EstadoCita estados) {
		EstadoCita estadosDb=service.create(estados);
		return ResponseEntity.status(HttpStatus.CREATED).body(estadosDb);
	}
	
	@PutMapping
	public ResponseEntity<EstadoCita> update(@RequestBody EstadoCita estados) {
		EstadoCita estadosDb=service.update(estados);
		return ResponseEntity.status(HttpStatus.CREATED).body(estadosDb);
	}
	
	@DeleteMapping(value="/{id}")
	public int delete(@PathVariable("id") int id){
		return service.delete(id);
	}
}
