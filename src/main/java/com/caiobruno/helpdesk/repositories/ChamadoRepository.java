package com.caiobruno.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.caiobruno.helpdesk.domain.Chamado;


public interface ChamadoRepository extends JpaRepository<Chamado, Integer>  {
    
	
}
