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

import com.example.SistemaGinecoFemWeb.entity.Paciente;
import com.example.SistemaGinecoFemWeb.entity.Preescripcion;
import com.example.SistemaGinecoFemWeb.services.PacienteService;
import com.example.SistemaGinecoFemWeb.services.PreescripcionService;



@RestController
@RequestMapping("/api/preescripciones")
public class PreescripcionApi {
	@Autowired
	private PreescripcionService service;
	
	@Autowired
	private PacienteService servicePa;
	
	@Autowired
	//private UsuarioService serviceUsu;
	
	@GetMapping()
	public ResponseEntity<List<Preescripcion>> getAll(){
		List<Preescripcion> preescripcion = service.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(preescripcion);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Preescripcion> getById(@PathVariable("id") int id) {
		Preescripcion preescripcion = service.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(preescripcion);
	}
	
	@PostMapping
	public ResponseEntity<Preescripcion> create(@RequestBody Preescripcion preescripcion) {
		Preescripcion preescripcionDb=service.create(preescripcion);
		return ResponseEntity.status(HttpStatus.CREATED).body(preescripcionDb);
	}
	
	@PutMapping
	public ResponseEntity<Preescripcion> update(@RequestBody Preescripcion preescripcion) {
		Preescripcion preescripcionDb=service.update(preescripcion);
		return ResponseEntity.status(HttpStatus.CREATED).body(preescripcionDb);
	}
	
	@DeleteMapping(value="/{id}")
	public int delete(@PathVariable("id") int id){
		return service.delete(id);
	}
	
	@GetMapping("/pacientes")
    public List<Paciente> getPacientes() {
        return servicePa.findAll();
    }
}
