package com.caiobruno.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caiobruno.helpdesk.domain.Chamado;
import com.caiobruno.helpdesk.repositories.ChamadoRepository;
import com.caiobruno.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class ChamadoService {

	
	@Autowired
	private ChamadoRepository repository;
	
	public Chamado findById(Integer id) {
		Optional<Chamado> obj= repository.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado ID :"+ id));
		
	}

	public List<Chamado> findById() {
		return repository.findAll();
	}
	
}
