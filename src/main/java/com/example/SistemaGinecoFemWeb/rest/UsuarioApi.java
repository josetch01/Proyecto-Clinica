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

import com.example.SistemaGinecoFemWeb.converters.UsuarioConverter;
import com.example.SistemaGinecoFemWeb.dto.UsuarioDTO;
import com.example.SistemaGinecoFemWeb.entity.Usuario;
import com.example.SistemaGinecoFemWeb.services.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioApi {
	
	@Autowired
	private UsuarioService serviceUsu;
	@Autowired
	private UsuarioConverter converterUsu;
	@GetMapping()
	public ResponseEntity<List<UsuarioDTO>> getAll(){
		List<Usuario> usuario = serviceUsu.findAll();
		List<UsuarioDTO> usuarioDTO = converterUsu.fromEntity(usuario);
		return ResponseEntity.status(HttpStatus.OK).body(usuarioDTO);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<UsuarioDTO> getById(@PathVariable("id") int id) {
		Usuario usuario = serviceUsu.findById(id);
		UsuarioDTO usuarioDTO=converterUsu.fromEntity(usuario);
		return ResponseEntity.status(HttpStatus.OK).body(usuarioDTO);
	}
	
	@PostMapping
	public ResponseEntity<Usuario> create(@RequestBody Usuario Usuario) {
		Usuario usuarioDb=serviceUsu.create(Usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDb);
	}
	
	@PutMapping
	public ResponseEntity<Usuario> update(@RequestBody Usuario Usuario) {
		Usuario usuarioDb=serviceUsu.update(Usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDb);
	}
	@DeleteMapping(value="/{id}")
	public int delete(@PathVariable("id") int id){
		return serviceUsu.desactivate(id);
	}
}
	