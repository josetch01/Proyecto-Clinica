package com.example.SistemaGinecoFemWeb.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.SistemaGinecoFemWeb.entity.Cita;
import com.example.SistemaGinecoFemWeb.exceptions.GeneralServiceException;
import com.example.SistemaGinecoFemWeb.exceptions.NoDataFoundException;
import com.example.SistemaGinecoFemWeb.exceptions.ValidateServiceException;
import com.example.SistemaGinecoFemWeb.repository.CitaRepository;
import com.example.SistemaGinecoFemWeb.services.CitaService;
import com.example.SistemaGinecoFemWeb.validators.CitaValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CitaServiceImpl implements CitaService{
	@Autowired
	private CitaRepository repository;

	@Override
	@Transactional(readOnly = true)
	public List<Cita> findAll() {
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
	public Cita findById(int id) {
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
	public Cita findByHistoria(String historia_clinica) {
		try {
			return repository.findByHistoria(historia_clinica);
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
	public List<Cita> findByHistoriaContaining(String historia_clinica) {
		try {
			return repository.findByHistoriaContaining(historia_clinica);
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
	public Cita create(Cita obj) {
		try {
			//Validación
			CitaValidator.save(obj);
			Cita cita=findByHistoria(obj.getHistoria());
			if(cita!=null) {
				throw new ValidateServiceException("Ya hay un registro con esa Historia Clinica");
			}
			//Guardamos
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
	public Cita update(Cita obj) {
		try {
			CitaValidator.save(obj);
			Cita citaDb=findById(obj.getId());
			if(citaDb==null) {
				throw new ValidateServiceException("No hay un registro con ese ID");
			}
			//Validación de nombre repetido
			Cita cita=findByHistoria(obj.getHistoria());
			if(cita!=null && obj.getId()!=cita.getId()) {
				throw new ValidateServiceException("Ya hay un registro con esa Historia Clinica");
			}
			//Actualizamos la categoría
			citaDb.setHistoria(obj.getHistoria());
			citaDb.setFecha_cita(obj.getFecha_cita());
			citaDb.setPaciente(obj.getPaciente());
			citaDb.setMedico(obj.getMedico());
			citaDb.setEstado(obj.getEstado());
			citaDb.setProducto(obj.getProducto());
			return repository.save(citaDb);
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
			Cita citaDb= findById(id);
			if(citaDb==null) {
				return 0;
			}else {
				repository.delete(citaDb);
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
