package com.brunomartin.cursomc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.brunomartin.cursomc.domain.Categoria;
import com.brunomartin.cursomc.exceptions.DataIntegrityException;
import com.brunomartin.cursomc.exceptions.ObjectNotFoundException;
import com.brunomartin.cursomc.repository.CategoriaRepository;



@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;
	
	public Categoria find(Integer id) {
		return repository.findById(id).orElseThrow(() -> 
					new ObjectNotFoundException("Objeto nao encontrado: "+id+" Tipo: "+Categoria.class.getName()));
	}
	
	public Categoria create(Categoria c) {
		return repository.save(c);
	}
	
	public Categoria update(Integer id) {
		Categoria c = find(id);
		return repository.save(c);
	}

	public void delete(Integer id) {
		Categoria c = find(id);
		try {
			repository.delete(c);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma categoria que possui produtos");
		}
	}
}
