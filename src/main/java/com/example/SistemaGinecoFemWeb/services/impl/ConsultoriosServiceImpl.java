package com.example.SistemaGinecoFemWeb.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.SistemaGinecoFemWeb.entity.Consultorio;
import com.example.SistemaGinecoFemWeb.exceptions.GeneralServiceException;
import com.example.SistemaGinecoFemWeb.exceptions.NoDataFoundException;
import com.example.SistemaGinecoFemWeb.exceptions.ValidateServiceException;
import com.example.SistemaGinecoFemWeb.repository.ConsultorioRepository;
import com.example.SistemaGinecoFemWeb.services.ConsultorioService;
import com.example.SistemaGinecoFemWeb.validators.ConsultorioValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ConsultoriosServiceImpl implements ConsultorioService{
	@Autowired
	private ConsultorioRepository repository;

	@Override
	@Transactional(readOnly = true)
	public List<Consultorio> findAll() {
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
	public Consultorio findById(int id) {
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
	public Consultorio findByNombre(String nombre) {
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
	public List<Consultorio> findByNombreContaining(String nombre) {
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
	public Consultorio create(Consultorio obj) {
		try {
			//Validación
			ConsultorioValidator.save(obj);
			Consultorio consultorio=findByNombre(obj.getNombre());
			if(consultorio!=null) {
				throw new ValidateServiceException("Ya hay un registro con ese nombre");
			}
			//Guardamos el consultorio
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
	public Consultorio update(Consultorio obj) {
		try {
			ConsultorioValidator.save(obj);
			Consultorio consultorioDB=findById(obj.getId());
			if(consultorioDB==null) {
				throw new ValidateServiceException("No hay un registro con ese ID");
			}
			//Validación de nombre repetido
			Consultorio consultorio=findByNombre(obj.getNombre());
			if(consultorio!=null && obj.getId()!=consultorio.getId()) {
				throw new ValidateServiceException("Ya hay un registro con ese nombre");
			}
			//Actualizamos el consultorio
			consultorioDB.setNombre(obj.getNombre());
			consultorioDB.setCodigo(obj.getCodigo());
			return repository.save(consultorioDB);
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
			Consultorio consultorioDB= findById(id);
			if(consultorioDB==null) {
				return 0;
			}else {
				repository.delete(consultorioDB);
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
