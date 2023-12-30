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

import com.example.SistemaGinecoFemWeb.converters.CitaConverter;
import com.example.SistemaGinecoFemWeb.dto.CitaDTO;
import com.example.SistemaGinecoFemWeb.entity.Cita;
import com.example.SistemaGinecoFemWeb.entity.EstadoCita;
import com.example.SistemaGinecoFemWeb.entity.Medico;
import com.example.SistemaGinecoFemWeb.entity.Paciente;
import com.example.SistemaGinecoFemWeb.entity.Producto;
import com.example.SistemaGinecoFemWeb.services.CitaService;
import com.example.SistemaGinecoFemWeb.services.EstadoCitaService;
import com.example.SistemaGinecoFemWeb.services.MedicoService;
import com.example.SistemaGinecoFemWeb.services.PacienteService;
import com.example.SistemaGinecoFemWeb.services.ProductoService;



@RestController
@RequestMapping("/api/citas")
public class CitaApi {
	@Autowired
	private CitaService service;
	@Autowired
	private PacienteService servicePac;
	@Autowired
	private MedicoService serviceMed;
	@Autowired
	private EstadoCitaService serviceEs;
	@Autowired
	private ProductoService servicePro;
	@Autowired
	private CitaConverter convertercit;
	@GetMapping()
	public ResponseEntity<List<CitaDTO>> getAll(){
		List<Cita> citas= service.findAll();
		List<CitaDTO> citasDTO = convertercit.fromEntity(citas);
		return ResponseEntity.status(HttpStatus.OK).body(citasDTO);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<CitaDTO> getById(@PathVariable("id") int id) {
		Cita cita = service.findById(id);
		CitaDTO citaDTO=convertercit.fromEntity(cita);
		return ResponseEntity.status(HttpStatus.OK).body(citaDTO);
	}
	
	@PostMapping
	public ResponseEntity<Cita> create(@RequestBody Cita cita) {
		Cita citaDb=service.create(cita);
		return ResponseEntity.status(HttpStatus.CREATED).body(citaDb);
	}
	
	@PutMapping
	public ResponseEntity<Cita> update(@RequestBody Cita cita) {
		Cita citaDb=service.update(cita);
		return ResponseEntity.status(HttpStatus.CREATED).body(citaDb);
	}
	
	@DeleteMapping(value="/{id}")
	public int delete(@PathVariable("id") int id){
		return service.delete(id);
	}
	
	@GetMapping("/pacientes")
    public List<Paciente> getPacientes() {
        return servicePac.findAll();
	}
	@GetMapping("/medicos")
    public List<Medico> getMedicos() {
        return serviceMed.findAll();
	}
	@GetMapping("/estados")
    public List<EstadoCita> getEstados() {
        return serviceEs.findAll();
	}
	@GetMapping("/productos")
    public List<Producto> getProductos() {
        return servicePro.findAll();
	}
}
