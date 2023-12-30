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

import com.example.SistemaGinecoFemWeb.converters.PacienteConverter;
import com.example.SistemaGinecoFemWeb.dto.PacienteDTO;
import com.example.SistemaGinecoFemWeb.entity.Paciente;
import com.example.SistemaGinecoFemWeb.services.PacienteService;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteApi {
	@Autowired
	private PacienteService service;
	@Autowired
	private PacienteConverter converterPac;
	@GetMapping()
	public ResponseEntity<List<PacienteDTO>> getAll(){
		List<Paciente> pacientes= service.findAll();
		List<PacienteDTO> pacientesDTO = converterPac.fromEntity(pacientes);
		return ResponseEntity.status(HttpStatus.OK).body(pacientesDTO);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<PacienteDTO> getById(@PathVariable("id") int id) {
		Paciente paciente = service.findById(id);
		PacienteDTO pacienteDTO=converterPac.fromEntity(paciente);
		return ResponseEntity.status(HttpStatus.OK).body(pacienteDTO);
	}
	
	@PostMapping
	public ResponseEntity<Paciente> create(@RequestBody Paciente paciente) {
		Paciente pacienteDb=service.create(paciente);
		return ResponseEntity.status(HttpStatus.CREATED).body(pacienteDb);
	}
	
	@PutMapping
	public ResponseEntity<Paciente> update(@RequestBody Paciente paciente) {
		Paciente pacienteDb=service.update(paciente);
		return ResponseEntity.status(HttpStatus.CREATED).body(pacienteDb);
	}
	
	@DeleteMapping(value="/{id}")
	public int delete(@PathVariable("id") int id){
		return service.delete(id);
	}
}
