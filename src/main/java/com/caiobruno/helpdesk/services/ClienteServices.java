package com.caiobruno.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caiobruno.helpdesk.domain.Pessoa;
import com.caiobruno.helpdesk.domain.Cliente;
import com.caiobruno.helpdesk.domain.dtos.ClienteDto;
import com.caiobruno.helpdesk.repositories.PessoaRepository;
import com.caiobruno.helpdesk.repositories.ClienteRepository;
import com.caiobruno.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.caiobruno.helpdesk.services.exceptions.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class ClienteServices {

	@Autowired
	private ClienteRepository repository;

	@Autowired
	private PessoaRepository pessoaRepository;

	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado id : " + id));
	}

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	public Cliente create(ClienteDto objDTO) {
		objDTO.setId(null);
		validarPorCpfEmail(objDTO);
		Cliente newObj = new Cliente(objDTO);
		return repository.save(newObj);

	}

	public Cliente update(Integer id, @Valid ClienteDto objDto) {
		objDto.setId(id);
		Cliente oldObj = findById(id);
		validarPorCpfEmail(objDto);
		oldObj = new Cliente(objDto);
		return repository.save(oldObj);
	}

	public void delete(Integer id) {
      Cliente obj = findById(id);
      if (obj.getChamados().size() > 0) {
		throw new DataIntegrityViolationException("Cliente possui ordens de serviços e nao pode ser deletado !");
	}
      repository.deleteById(id);
      
	}

	private void validarPorCpfEmail(ClienteDto objDTO) {
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
