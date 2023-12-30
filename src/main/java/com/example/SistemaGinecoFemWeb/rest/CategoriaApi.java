package com.example.SistemaGinecoFemWeb.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.SistemaGinecoFemWeb.converters.CategoriaConverter;
import com.example.SistemaGinecoFemWeb.dto.CategoriaDTO;
import com.example.SistemaGinecoFemWeb.entity.Categoria;
import com.example.SistemaGinecoFemWeb.services.CategoriaService;


@RestController
@RequestMapping("/api/categorias")
public class CategoriaApi {
	@Autowired
	private CategoriaService service;
	@Autowired
	private CategoriaConverter converterCat;
	@GetMapping()
	public ResponseEntity<List<CategoriaDTO>> getAll(){
		List<Categoria> categorias= service.findAll();
		List<CategoriaDTO> categoriaDTO = converterCat.fromEntity(categorias);
		return ResponseEntity.status(HttpStatus.OK).body(categoriaDTO);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<CategoriaDTO> getById(@PathVariable("id") int id) {
		Categoria categoria = service.findById(id);
		CategoriaDTO categoriaDTO=converterCat.fromEntity(categoria);
		return ResponseEntity.status(HttpStatus.OK).body(categoriaDTO);
	}
	
	@PostMapping
	public ResponseEntity<Categoria> create(@RequestBody Categoria categoria) {
		Categoria categoriaDb=service.create(categoria);
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaDb);
	}
	
	@PutMapping
	public ResponseEntity<Categoria> update(@RequestBody Categoria categoria) {
		Categoria categoriaDb=service.update(categoria);
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaDb);
	}
	
	@DeleteMapping(value="/{id}")
	public int delete(@PathVariable("id") int id){
		return service.delete(id);
	}
}
