package com.caiobruno.helpdesk.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.caiobruno.helpdesk.domain.Cliente;
import com.caiobruno.helpdesk.domain.dtos.ClienteDto;
import com.caiobruno.helpdesk.services.ClienteServices;

import jakarta.validation.Valid;



@RestController
@RequestMapping(value = "/clientes")
public class ClienteResources {

	@Autowired
	private ClienteServices services; 
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClienteDto> findById(@PathVariable Integer id){
		Cliente obj =services.findById(id);
		return ResponseEntity.ok().body(new ClienteDto(obj));
	}
	
	@GetMapping
	public ResponseEntity<List<ClienteDto>> findAll(){
		List<Cliente> list = services.findAll();
		List<ClienteDto> listDTO = list.stream().map(obj -> new ClienteDto(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDTO);
	}
	
	@PostMapping
	public ResponseEntity<ClienteDto> create(@Valid @RequestBody ClienteDto objDTO){
          Cliente newobj = services.create(objDTO);
          URI  uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newobj.getId()).toUri();
          return ResponseEntity.created(uri).body(objDTO);
   
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<ClienteDto> update(@PathVariable Integer id , @Valid @RequestBody ClienteDto objDto){
		Cliente obj =services.update(id, objDto);
		return ResponseEntity.ok().body(new ClienteDto(obj));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ClienteDto> delete(@PathVariable Integer id ){
		services.delete(id);
		return ResponseEntity.noContent().build();
	}
}
