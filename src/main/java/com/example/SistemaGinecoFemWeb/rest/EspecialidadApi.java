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

import com.example.SistemaGinecoFemWeb.converters.EspecialidadConverter;
import com.example.SistemaGinecoFemWeb.dto.EspecialidadDTO;
import com.example.SistemaGinecoFemWeb.entity.Especialidad;
import com.example.SistemaGinecoFemWeb.services.EspecialidadService;

@RestController
@RequestMapping("/api/especialidades")
public class EspecialidadApi {
	@Autowired
	private EspecialidadService service;
	
	@Autowired
	private EspecialidadConverter converterEsp;
	@GetMapping()
	public ResponseEntity<List<EspecialidadDTO>> getAll(){
		List<Especialidad> especialdiades= service.findAll();
		List<EspecialidadDTO> especialidadDTO = converterEsp.fromEntity(especialdiades);
		return ResponseEntity.status(HttpStatus.OK).body(especialidadDTO);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<EspecialidadDTO> getById(@PathVariable("id") int id) {
		Especialidad especialdiad = service.findById(id);
		EspecialidadDTO especialidadDTO=converterEsp.fromEntity(especialdiad);
		return ResponseEntity.status(HttpStatus.OK).body(especialidadDTO);
	}
	
	@PostMapping
	public ResponseEntity<Especialidad> create(@RequestBody Especialidad especialidad) {
		Especialidad especialidadDB=service.create(especialidad);
		return ResponseEntity.status(HttpStatus.CREATED).body(especialidadDB);
	}
	
	@PutMapping
	public ResponseEntity<Especialidad> update(@RequestBody Especialidad especialidad) {
		Especialidad especialidadDB=service.update(especialidad);
		return ResponseEntity.status(HttpStatus.CREATED).body(especialidadDB);
	}
	
	@DeleteMapping(value="/{id}")
	public int delete(@PathVariable("id") int id){
		return service.delete(id);
	}
}
