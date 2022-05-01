package com.rodrigo.testevenda.resources;


import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

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

import com.rodrigo.testevenda.domain.Venda;
import com.rodrigo.testevenda.dtos.VendaDTO;
import com.rodrigo.testevenda.services.VendaService;

@RestController
@RequestMapping("/vendas")
public class VendaResource {

	@Autowired
	private VendaService service;
	
	@GetMapping
	public ResponseEntity<List<VendaDTO>> findAll(){
		List<Venda> list = service.findAll();
		List<VendaDTO> listDTO = list.stream().map(obj -> new VendaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<VendaDTO> findById(@PathVariable Integer id){
		Venda obj = service.findById(id);
		return ResponseEntity.ok().body(new VendaDTO(obj));
	}
	
	@PostMapping
	public ResponseEntity<VendaDTO> create(@Valid @RequestBody VendaDTO dto){
		Venda obj = service.create(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}  
	
	@PutMapping(value ="/{id}")
	public ResponseEntity<VendaDTO> update(@PathVariable Integer id, @Valid @RequestBody VendaDTO objDTO) {
		Venda obj = service.update(id, objDTO);
		return ResponseEntity.ok().body(new VendaDTO(obj));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<VendaDTO> delete(@PathVariable Integer id ){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
