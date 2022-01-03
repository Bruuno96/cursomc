package com.brunomartin.cursomc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brunomartin.cursomc.domain.Cliente;
import com.brunomartin.cursomc.exceptions.ObjectNotFoundException;
import com.brunomartin.cursomc.repository.ClienteRepository;



@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	public Cliente find(Integer id) {
		return repository.findById(id).orElseThrow(() -> 
					new ObjectNotFoundException("Objeto nao encontrado: "+id+" Tipo: "+Cliente.class.getName()));
	}
	
	public Cliente create(Cliente c) {
		return repository.save(c);
	}
}
