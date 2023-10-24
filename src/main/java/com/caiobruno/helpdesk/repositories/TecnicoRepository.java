package com.caiobruno.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.caiobruno.helpdesk.domain.Tecnico;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer>  {

}
