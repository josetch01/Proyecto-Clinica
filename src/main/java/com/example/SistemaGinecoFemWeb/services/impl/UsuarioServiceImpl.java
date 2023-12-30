package com.example.SistemaGinecoFemWeb.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.SistemaGinecoFemWeb.components.Encoder;
import com.example.SistemaGinecoFemWeb.entity.Usuario;
import com.example.SistemaGinecoFemWeb.exceptions.GeneralServiceException;
import com.example.SistemaGinecoFemWeb.exceptions.NoDataFoundException;
import com.example.SistemaGinecoFemWeb.exceptions.ValidateServiceException;
import com.example.SistemaGinecoFemWeb.repository.UsuarioRepository;
import com.example.SistemaGinecoFemWeb.services.UsuarioService;
import com.example.SistemaGinecoFemWeb.validators.UsuarioValidator;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private UsuarioRepository repository;
	@Autowired
	private Encoder encoder;
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		try {
			return repository.findAll();
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findById(int id) {
		try {
			return repository.findById(id).orElse(null);
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findByEmail(String email) {
		try {
			return repository.findByEmail(email);
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public Usuario create(Usuario obj) {
		try {
			//Validación
			UsuarioValidator.save(obj);
			Usuario usuario=findByEmail(obj.getEmail());
			if(usuario!=null) {
				throw new ValidateServiceException("Ya hay un registro con ese Email");
			}
			//Guardamos
			String password=obj.getPassword();
			obj.setPassword(encoder.encodePassword(password));
			obj.setActivo(true);
			return repository.save(obj);
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public Usuario update(Usuario obj) {
		try {
			UsuarioValidator.save(obj);
			Usuario usuarioDb=findById(obj.getId());
			if(usuarioDb==null) {
				throw new ValidateServiceException("No hay un registro con ese ID");
			}
			//Validación de nombre repetido
			Usuario usuario=findByEmail(obj.getEmail());
			if(usuario!=null && obj.getId()!=usuario.getId()) {
				throw new ValidateServiceException("Ya hay un registro con ese Email");
			}
			//Actualizamos la categoría
			usuarioDb.setNombre(obj.getNombre());
			usuarioDb.setEmail(obj.getEmail());
			String password=obj.getPassword();
			usuarioDb.setPassword(encoder.encodePassword(password));
			return repository.save(usuarioDb);
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	public int activate(int id) {
		try {
			Usuario usuarioDb=findById(id);
			if(usuarioDb==null) {
				throw new ValidateServiceException("No hay un registro con ese ID");
			}
			usuarioDb.setActivo(true);
			repository.save(usuarioDb);
			return 1;
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	public int desactivate(int id) {
		try {
			Usuario usuarioDb=findById(id);
			if(usuarioDb==null) {
				throw new ValidateServiceException("No hay un registro con ese ID");
			}
			usuarioDb.setActivo(false);
			repository.save(usuarioDb);
			return 1;
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

}
