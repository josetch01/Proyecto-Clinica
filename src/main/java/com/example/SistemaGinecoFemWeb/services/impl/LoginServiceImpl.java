package com.example.SistemaGinecoFemWeb.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.SistemaGinecoFemWeb.entity.Usuario;
import com.example.SistemaGinecoFemWeb.services.UsuarioService;

@Service
public class LoginServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario appUser = usuarioService.findByEmail(email);	
		UserDetails user = User.withUsername(appUser.getEmail())
			.password(appUser.getPassword())
			.authorities("ADMIN")
			.build();
		return user;		
	}
}