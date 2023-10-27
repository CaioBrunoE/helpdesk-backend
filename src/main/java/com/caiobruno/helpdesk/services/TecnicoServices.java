package com.caiobruno.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caiobruno.helpdesk.domain.Pessoa;
import com.caiobruno.helpdesk.domain.Tecnico;
import com.caiobruno.helpdesk.domain.dtos.TecnicoDto;
import com.caiobruno.helpdesk.repositories.PessoaRepository;
import com.caiobruno.helpdesk.repositories.TecnicoRepository;
import com.caiobruno.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.caiobruno.helpdesk.services.exceptions.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class TecnicoServices {

	@Autowired
	private TecnicoRepository repository;

	@Autowired
	private PessoaRepository pessoaRepository;

	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado id : " + id));
	}

	public List<Tecnico> findAll() {
		return repository.findAll();
	}

	public Tecnico create(TecnicoDto objDTO) {
		objDTO.setId(null);
		validarPorCpfEmail(objDTO);
		Tecnico newObj = new Tecnico(objDTO);
		return repository.save(newObj);

	}
	
	public Tecnico update(Integer id, @Valid TecnicoDto objDto) {
		objDto.setId(id);
		Tecnico oldObj =  findById(id);
		validarPorCpfEmail(objDto);
		oldObj = new Tecnico(objDto);
		return repository.save(oldObj);
	}

	private void validarPorCpfEmail(TecnicoDto objDTO) {
		Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());

		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("CPF ja cadastrado no sistema");
		}

		obj = pessoaRepository.findByEmail(objDTO.getEmail());

		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("Email ja cadastrado ");
		}
	}

	
}
