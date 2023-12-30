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

import com.example.SistemaGinecoFemWeb.converters.ConsultorioConverter;
import com.example.SistemaGinecoFemWeb.dto.ConsultorioDTO;
import com.example.SistemaGinecoFemWeb.entity.Consultorio;
import com.example.SistemaGinecoFemWeb.services.ConsultorioService;



@RestController
@RequestMapping("/api/consultorios")
public class ConsultorioApi {
	@Autowired
	private ConsultorioService service;
	@Autowired
	private ConsultorioConverter converterCon;
	@GetMapping()
	public ResponseEntity<List<ConsultorioDTO>> getAll(){
		List<Consultorio> consultorios= service.findAll();
		List<ConsultorioDTO> consultorioDTO = converterCon.fromEntity(consultorios);
		return ResponseEntity.status(HttpStatus.OK).body(consultorioDTO);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<ConsultorioDTO> getById(@PathVariable("id") int id) {
		Consultorio consultorio = service.findById(id);
		ConsultorioDTO consultorioDTO=converterCon.fromEntity(consultorio);
		return ResponseEntity.status(HttpStatus.OK).body(consultorioDTO);
	}
	
	@PostMapping
	public ResponseEntity<Consultorio> create(@RequestBody Consultorio consultorio) {
		Consultorio consultorioDB=service.create(consultorio);
		return ResponseEntity.status(HttpStatus.CREATED).body(consultorioDB);
	}
	
	@PutMapping
	public ResponseEntity<Consultorio> update(@RequestBody Consultorio consultorio) {
		Consultorio consultorioDB=service.update(consultorio);
		return ResponseEntity.status(HttpStatus.CREATED).body(consultorioDB);
	}
	
	@DeleteMapping(value="/{id}")
	public int delete(@PathVariable("id") int id){
		return service.delete(id);
	}
}
