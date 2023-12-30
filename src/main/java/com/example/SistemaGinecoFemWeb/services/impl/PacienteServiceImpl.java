package com.example.SistemaGinecoFemWeb.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.SistemaGinecoFemWeb.entity.Paciente;
import com.example.SistemaGinecoFemWeb.exceptions.GeneralServiceException;
import com.example.SistemaGinecoFemWeb.exceptions.NoDataFoundException;
import com.example.SistemaGinecoFemWeb.exceptions.ValidateServiceException;
import com.example.SistemaGinecoFemWeb.repository.PacienteRepository;
import com.example.SistemaGinecoFemWeb.services.PacienteService;
import com.example.SistemaGinecoFemWeb.validators.PacienteValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PacienteServiceImpl implements PacienteService{
	@Autowired
	private PacienteRepository repository;
	@Override
	@Transactional(readOnly = true)
	public List<Paciente> findAll() {
		try {
			return repository.findAll();
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		}catch(Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Paciente findById(int id) {
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
	public Paciente findByDocumento(String documento) {
		try {
			return repository.findByDocumento(documento);
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
	public List<Paciente> findByDocumentoContaining(String documento) {
		try {
			return repository.findByDocumentoContaining(documento);
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
	public Paciente create(Paciente obj) {
		try {
			//Validación
			PacienteValidator.save(obj);
			Paciente paciente=findByDocumento(obj.getDocumento());
			if(paciente!=null) {
				throw new ValidateServiceException("Ya hay un registro con ese documento");
			}
			//Guardamos la categoría
			obj.setEstado("Activo");
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
	public Paciente update(Paciente obj) {
		try {
			PacienteValidator.save(obj);
			Paciente pacienteDb=findById(obj.getId());
			if(pacienteDb==null) {
				throw new ValidateServiceException("No hay un registro con ese ID");
			}
			//Validación de nombre repetido
			Paciente paciente=findByDocumento(obj.getDocumento());
			if(paciente!=null && obj.getId()!=paciente.getId()) {
				throw new ValidateServiceException("Ya hay un registro con ese Documento");
			}
			//Actualizamos el paciente
			pacienteDb.setNombre(obj.getNombre());
			pacienteDb.setApellidos(obj.getApellidos());
			pacienteDb.setDocumento(obj.getDocumento());
			pacienteDb.setNacimiento(obj.getNacimiento());
			pacienteDb.setTelefono(obj.getTelefono());
			pacienteDb.setDireccion(obj.getDireccion());
			pacienteDb.setCiudad(obj.getCiudad());
			return repository.save(pacienteDb);
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
	public int delete(int id) {
		try {
			Paciente pacienteDb= findById(id);
			if(pacienteDb==null) {
				return 0;
			}else {
				pacienteDb.setEstado("Inactivo");
				repository.save(pacienteDb);
				return 1;
			}
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

}
