package com.example.SistemaGinecoFemWeb.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.SistemaGinecoFemWeb.converters.ProductoConverter;
import com.example.SistemaGinecoFemWeb.dto.ProductoDTO;
import com.example.SistemaGinecoFemWeb.entity.Categoria;
import com.example.SistemaGinecoFemWeb.entity.Producto;
import com.example.SistemaGinecoFemWeb.services.CategoriaService;
import com.example.SistemaGinecoFemWeb.services.ProductoService;





@RestController
@RequestMapping("/api/productos")
public class ProductoApi {
	@Autowired
	private ProductoService service;
	
	@Autowired
	private CategoriaService serviceCat;
	@Autowired
	private ProductoConverter converterprod;
	@GetMapping()
	public ResponseEntity<List<ProductoDTO>> getAll(){
		List<Producto>productos= service.findAll();
		List<ProductoDTO> productoDTO = converterprod.fromEntity(productos);
		return ResponseEntity.status(HttpStatus.OK).body(productoDTO);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<ProductoDTO> getById(@PathVariable("id") int id) {
		Producto producto = service.findById(id);
		ProductoDTO productoDTO=converterprod.fromEntity(producto);
		return ResponseEntity.status(HttpStatus.OK).body(productoDTO);
	}
	
	@PostMapping
	public ResponseEntity<Producto> create(@RequestBody Producto producto) {
		Producto productoDb=service.create(producto);
		return ResponseEntity.status(HttpStatus.CREATED).body(productoDb);
	}
	
	@PutMapping
	public ResponseEntity<Producto> update(@RequestBody Producto producto) {
		Producto productoDb=service.update(producto);
		return ResponseEntity.status(HttpStatus.CREATED).body(productoDb);
	}
	
	@DeleteMapping(value="/{id}")
	public int delete(@PathVariable("id") int id){
		return service.delete(id);
	}
	
	@GetMapping("/categorias")
    public List<Categoria> getCategorias() {
        return serviceCat.findAll();
    }
	
}
