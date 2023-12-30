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

import com.example.SistemaGinecoFemWeb.converters.MedicoConverter;
import com.example.SistemaGinecoFemWeb.dto.MedicoDTO;
import com.example.SistemaGinecoFemWeb.entity.Consultorio;
import com.example.SistemaGinecoFemWeb.entity.Especialidad;
import com.example.SistemaGinecoFemWeb.entity.Medico;
import com.example.SistemaGinecoFemWeb.services.ConsultorioService;
import com.example.SistemaGinecoFemWeb.services.EspecialidadService;
import com.example.SistemaGinecoFemWeb.services.MedicoService;

@RestController
@RequestMapping("/api/medicos")
public class MedicoApi {
	@Autowired
	private MedicoService service;
	@Autowired
	private MedicoConverter converterMed;
	@Autowired
	private EspecialidadService serviceEsp;
	@Autowired
	private ConsultorioService serviceCon;
	
	@GetMapping()
	public ResponseEntity<List<MedicoDTO>> getAll(){
		List<Medico> medicos= service.findAll();
		List<MedicoDTO> medicosDTO = converterMed.fromEntity(medicos);
		return ResponseEntity.status(HttpStatus.OK).body(medicosDTO);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<MedicoDTO> getById(@PathVariable("id") int id) {
		Medico medico = service.findById(id);
		MedicoDTO medicoDTO=converterMed.fromEntity(medico);
		return ResponseEntity.status(HttpStatus.OK).body(medicoDTO);
	}
	
	@PostMapping
	public ResponseEntity<Medico> create(@RequestBody Medico medico) {
		Medico medicoDb=service.create(medico);
		return ResponseEntity.status(HttpStatus.CREATED).body(medicoDb);
	}
	
	@PutMapping
	public ResponseEntity<Medico> update(@RequestBody Medico medico) {
		Medico medicoDb=service.update(medico);
		return ResponseEntity.status(HttpStatus.CREATED).body(medicoDb);
	}
	
	@DeleteMapping(value="/{id}")
	public int delete(@PathVariable("id") int id){
		return service.delete(id);
	}
	
	@GetMapping("/consultorios")
    public List<Consultorio> getConsultorios() {
        return serviceCon.findAll();
	}
	@GetMapping("/especialidades")
    public List<Especialidad> getEspecialidades() {
        return serviceEsp.findAll();
	}
}
