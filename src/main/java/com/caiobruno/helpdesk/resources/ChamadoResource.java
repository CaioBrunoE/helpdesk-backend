package com.caiobruno.helpdesk.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caiobruno.helpdesk.domain.Chamado;
import com.caiobruno.helpdesk.domain.dtos.ChamadoDto;
import com.caiobruno.helpdesk.services.ChamadoService;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadoResource {

	@Autowired
	private ChamadoService service;
	
	@GetMapping(value =  "/{id}")
	public ResponseEntity<ChamadoDto> findBuId(@PathVariable Integer id){
		Chamado obj = service.findById(id);
		return ResponseEntity.ok().body(new ChamadoDto(obj)); 
		
	}
	
	@GetMapping
	public ResponseEntity<List<ChamadoDto>> findAll(){
		List<Chamado> list = service.findById();
		List<ChamadoDto> listDTO= list.stream().map(obj-> new ChamadoDto(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
}