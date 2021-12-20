package com.brunomartin.cursomc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brunomartin.cursomc.domain.Categoria;
import com.brunomartin.cursomc.repository.CategoriaRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;



@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;
	
	public Categoria buscar(Integer id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No object with this ID"));
	}
	
	public Categoria create(Categoria c) {
		return repository.save(c);
	}
}
