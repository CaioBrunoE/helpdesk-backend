package com.caiobruno.helpdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caiobruno.helpdesk.domain.Chamado;
import com.caiobruno.helpdesk.domain.Cliente;
import com.caiobruno.helpdesk.domain.Tecnico;
import com.caiobruno.helpdesk.domain.enums.Perfil;
import com.caiobruno.helpdesk.domain.enums.Prioridade;
import com.caiobruno.helpdesk.domain.enums.Status;
import com.caiobruno.helpdesk.repositories.ChamadoRepository;
import com.caiobruno.helpdesk.repositories.ClienteRepository;
import com.caiobruno.helpdesk.repositories.TecnicoRepository;

@Service
public class DBService {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ChamadoRepository chamadoRepository;
	
	public void instaciaDB() {
		Tecnico tec1 = new Tecnico(null, "Valdir Cezar", "801.273.210-63", "valdir@gmail.com", "999");
		tec1.addPerfil(Perfil.ADMIN);

		tecnicoRepository.saveAll(Arrays.asList(tec1));
		Cliente cli1 = new Cliente(null, "Linus Torvald", "191.019.770-08", "linus@gmail.com", "123");
		cli1.addPerfil(Perfil.CLIENTE);
       
		clienteRepository.saveAll(Arrays.asList(cli1));
		
		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado01", "Primeiro chamado", tec1, cli1);
	   
		chamadoRepository.saveAll(Arrays.asList(c1));
	}
	
}
