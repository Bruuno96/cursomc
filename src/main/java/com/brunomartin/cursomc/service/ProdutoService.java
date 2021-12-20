package com.brunomartin.cursomc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.brunomartin.cursomc.domain.Produto;
import com.brunomartin.cursomc.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;
	
	public Produto findById(Integer i) {
		return repository.findById(i).orElseThrow(() -> new ResourceNotFoundException("Produto nao encontrado"));
	}
	
	public List<Produto> findAll(){
		return repository.findAll();
	}
	
}
