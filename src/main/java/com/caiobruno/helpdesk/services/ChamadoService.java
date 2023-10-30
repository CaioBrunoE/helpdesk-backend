package com.caiobruno.helpdesk.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caiobruno.helpdesk.domain.Chamado;
import com.caiobruno.helpdesk.domain.Cliente;
import com.caiobruno.helpdesk.domain.Tecnico;
import com.caiobruno.helpdesk.domain.dtos.ChamadoDto;
import com.caiobruno.helpdesk.domain.enums.Prioridade;
import com.caiobruno.helpdesk.domain.enums.Status;
import com.caiobruno.helpdesk.repositories.ChamadoRepository;
import com.caiobruno.helpdesk.services.exceptions.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class ChamadoService {

	@Autowired
	private ChamadoRepository repository;

	@Autowired
	private TecnicoServices tecnicoServices;

	@Autowired
	private ClienteServices clienteServices;

	public Chamado findById(Integer id) {
		Optional<Chamado> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado ID :" + id));

	}

	public List<Chamado> findById() {
		return repository.findAll();
	}

	public Chamado create(@Valid ChamadoDto objDto) {
		return repository.save(newChamado(objDto));
	}

	public Chamado update(Integer id, @Valid ChamadoDto objDto) {
		objDto.setId(id);
		Chamado oldObj = findById(id);
		oldObj = newChamado(objDto);

		return repository.save(oldObj);
	}

	private Chamado newChamado(ChamadoDto obj) {
		Tecnico tecnico = tecnicoServices.findById(obj.getTecnico());
		Cliente cliente = clienteServices.findById(obj.getCliente());

		Chamado chamado = new Chamado();
		if (obj.getId() != null) {
			chamado.setId(obj.getId());
		}
		
		if (obj.getStatus().equals(2)) {
			chamado.setDataFechamento(LocalDate.now());
		}

		chamado.setTecnico(tecnico);
		chamado.setCliente(cliente);
		chamado.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
		chamado.setStatus(Status.toEnum(obj.getStatus()));
		chamado.setTitulo(obj.getTitulo());
		chamado.setObservacoe(obj.getObservacoe());

		return chamado;
	}

}
