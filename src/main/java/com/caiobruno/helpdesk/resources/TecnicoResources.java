package com.caiobruno.helpdesk.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caiobruno.helpdesk.domain.Tecnico;
import com.caiobruno.helpdesk.domain.dtos.TecnicoDto;
import com.caiobruno.helpdesk.services.TecnicoServices;



@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResources {

	@Autowired
	private TecnicoServices services; 
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<TecnicoDto> findById(@PathVariable Integer id){
		Tecnico obj =services.findById(id);
		return ResponseEntity.ok().body(new TecnicoDto(obj));
	}
	
	@GetMapping
	public ResponseEntity<List<TecnicoDto>> findAll(){
		List<Tecnico> list = services.findAll();
		List<TecnicoDto> listDTO = list.stream().map(obj -> new TecnicoDto(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDTO);
	}
}
