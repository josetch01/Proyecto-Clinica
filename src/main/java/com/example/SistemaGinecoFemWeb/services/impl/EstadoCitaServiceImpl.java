package com.example.SistemaGinecoFemWeb.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.example.SistemaGinecoFemWeb.entity.EstadoCita;
import com.example.SistemaGinecoFemWeb.exceptions.GeneralServiceException;
import com.example.SistemaGinecoFemWeb.exceptions.NoDataFoundException;
import com.example.SistemaGinecoFemWeb.exceptions.ValidateServiceException;
import com.example.SistemaGinecoFemWeb.repository.EstadoCitaRepository;
import com.example.SistemaGinecoFemWeb.services.EstadoCitaService;
import com.example.SistemaGinecoFemWeb.validators.EstadoValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EstadoCitaServiceImpl implements EstadoCitaService{
	@Autowired
	private EstadoCitaRepository repository;

	@Override
	@Transactional(readOnly = true)
	public List<EstadoCita> findAll() {
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
	public EstadoCita findById(int id) {
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
	public EstadoCita findByNombre(String nombre) {
		try {
			return repository.findByNombre(nombre);
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
	public List<EstadoCita> findByNombreContaining(String nombre) {
		try {
			return repository.findByNombreContaining(nombre);
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
	public EstadoCita create(EstadoCita obj) {
		try {
			//Validación
			EstadoValidator.save(obj);
			EstadoCita estados=findByNombre(obj.getNombre());
			if(estados!=null) {
				throw new ValidateServiceException("Ya hay un registro con ese nombre");
			}
			//Guardamos la categoría
			obj.setEstado(1);
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
	public EstadoCita update(EstadoCita obj) {
		try {
			EstadoValidator.save(obj);
			EstadoCita estadosDb=findById(obj.getId());
			if(estadosDb==null) {
				throw new ValidateServiceException("No hay un registro con ese ID");
			}
			//Validacion  de nombre repetido
			EstadoCita estados=findByNombre(obj.getNombre());
			if(estados!=null && obj.getId()!=estados.getId()) {
				throw new ValidateServiceException("Ya hay un registro con ese nombre");
			}
			//Actualizamos el estado
			estadosDb.setNombre(obj.getNombre());
			return repository.save(estadosDb);
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
			EstadoCita estadosDb= findById(id);
			if(estadosDb==null) {
				return 0;
			}else {
				repository.delete(estadosDb);
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
