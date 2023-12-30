package com.example.SistemaGinecoFemWeb.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping("/")
    public String getEscritorio() {
		
		return "escritorio";
	}
	
	@GetMapping("/categorias")
    public String getCategorias(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("usuario", auth.getName());
		return "categoria";
	}
	@GetMapping("/productos")
    public String getProductos(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("usuario", auth.getName());
		return "producto";
	}
	@GetMapping("/especialidades")
    public String getEspecialidades(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("usuario", auth.getName());
		return "especialidades";
	}
	@GetMapping("/consultorios")
    public String getConsultorios(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("usuario", auth.getName());
		return "consultorios";
	}
	@GetMapping("/estados")
    public String getEstados(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("usuario", auth.getName());
		return "estado";
	}
	@GetMapping("/pacientes")
    public String getPacientes(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("usuario", auth.getName());
		return "paciente";
	}
	@GetMapping("/preescripciones")
    public String getPreescripciones(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("usuario", auth.getName());
		return "preescripcion";
	}
	@GetMapping("/medicos")
    public String getMedicos(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("usuario", auth.getName());
		return "medicos";
	}
	@GetMapping("/citas")
    public String getCitas(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("usuario", auth.getName());
		return "cita";
	}
	@GetMapping("/usuarios")
    public String getUsuarios() {
		return "usuario";
	}
	@GetMapping("/login")
    public String getLogin() {
		return "login";
	}
	
	@GetMapping("/error")
    public String getError(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("usuario", auth.getName());
		return "categoria";
	}
}
