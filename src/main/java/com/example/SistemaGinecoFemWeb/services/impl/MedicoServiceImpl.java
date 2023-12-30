package com.example.SistemaGinecoFemWeb.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.SistemaGinecoFemWeb.entity.Medico;
import com.example.SistemaGinecoFemWeb.exceptions.GeneralServiceException;
import com.example.SistemaGinecoFemWeb.exceptions.NoDataFoundException;
import com.example.SistemaGinecoFemWeb.exceptions.ValidateServiceException;
import com.example.SistemaGinecoFemWeb.repository.MedicoRepository;
import com.example.SistemaGinecoFemWeb.services.MedicoService;
import com.example.SistemaGinecoFemWeb.validators.MedicoValidator;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class MedicoServiceImpl implements MedicoService{
	@Autowired
	private MedicoRepository repository;

	@Override
	@Transactional(readOnly = true)
	public List<Medico> findAll() {
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
	public Medico findById(int id) {
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
	public Medico findByDocumento(String documento) {
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
	public List<Medico> findByDocumentoContaining(String documento) {
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
	public Medico create(Medico obj) {
		try {
			//Validación
			MedicoValidator.save(obj);
			Medico medico=findByDocumento(obj.getDocumento());
			if(medico!=null) {
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
	public Medico update(Medico obj) {
		try {
			MedicoValidator.save(obj);
			Medico medicoDb=findById(obj.getId());
			if(medicoDb==null) {
				throw new ValidateServiceException("No hay un registro con ese ID");
			}
			//Validación de nombre repetido
			Medico medico=findByDocumento(obj.getDocumento());
			if(medico!=null && obj.getId()!=medico.getId()) {
				throw new ValidateServiceException("Ya hay un registro con ese Documento");
			}
			//Actualizamos el paciente
			medicoDb.setNombre(obj.getNombre());
			medicoDb.setApellidos(obj.getApellidos());
			medicoDb.setDocumento(obj.getDocumento());
			medicoDb.setNacimiento(obj.getNacimiento());
			medicoDb.setTelefono(obj.getTelefono());
			medicoDb.setConsultorio(obj.getConsultorio());
			medicoDb.setEspecialidad(obj.getEspecialidad());
			return repository.save(medicoDb);
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
			Medico medicoDb= findById(id);
			if(medicoDb==null) {
				return 0;
			}else {
				medicoDb.setEstado("Inactivo");
				repository.save(medicoDb);
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
