package com.rodrigo.testevenda.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigo.testevenda.domain.Venda;
import com.rodrigo.testevenda.dtos.VendaDTO;
import com.rodrigo.testevenda.repositories.VendaRepository;
import com.rodrigo.testevenda.services.exceptions.ObjectNotFoundException;

@Service
public class VendaService {
	
	@Autowired
	private VendaRepository repository;
	
	public List<Venda> findAll() {
		return repository.findAll();
	}
	
	public Venda findById(Integer id) {
		Optional<Venda> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id));
	}
	
	public Venda create(VendaDTO dto) {
		dto.setId(null);
		Venda obj = new Venda(dto);
		return repository.save(obj);		
	}

	public Venda update(Integer id, @Valid VendaDTO objDTO) {
		objDTO.setId(id);
		Venda oldOBJ = findById(id);
		oldOBJ = new Venda(objDTO);
		return repository.save(oldOBJ);
	}

	public void delete(Integer id) {
		repository.deleteById(id);
	
	}
	
	
	
	
	
	
}
