package com.caiobruno.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.caiobruno.helpdesk.domain.Cliente;

public interface PessoaRepository extends JpaRepository<Cliente, Integer>  {
    
	
}
