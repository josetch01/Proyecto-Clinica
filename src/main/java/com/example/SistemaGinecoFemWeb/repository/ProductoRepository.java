package com.example.SistemaGinecoFemWeb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SistemaGinecoFemWeb.entity.Producto;



@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
	public Producto findByNombre(String nombre);
	public List<Producto> findByNombreContaining(String nombre);
}
