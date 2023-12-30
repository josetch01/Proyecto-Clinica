package com.example.SistemaGinecoFemWeb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SistemaGinecoFemWeb.entity.Categoria;



@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
	public Categoria findByNombre(String nombre);
	public List<Categoria> findByNombreContaining(String nombre);
}
