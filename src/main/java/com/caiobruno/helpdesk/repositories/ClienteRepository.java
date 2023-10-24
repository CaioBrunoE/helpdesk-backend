package com.caiobruno.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.caiobruno.helpdesk.domain.Pessoa;

public interface ClienteRepository extends JpaRepository<Pessoa, Integer>  {
    
	
}
