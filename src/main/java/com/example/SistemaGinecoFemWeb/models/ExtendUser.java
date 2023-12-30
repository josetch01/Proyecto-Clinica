package com.example.SistemaGinecoFemWeb.models;

import java.util.Collection;

import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;

import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
public class ExtendUser extends User {
	private static final long serialVersionUID = 1L;
	
	public ExtendUser(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authoritires) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authoritires);
	}

	private Integer id;
	private String nombre;
	private String email;
	private String userName;
	private String rol;
}
