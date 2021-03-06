package com.brunomartin.cursomc.service;

import java.util.List;

import com.brunomartin.cursomc.domain.Categoria;
import com.brunomartin.cursomc.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.brunomartin.cursomc.domain.Produto;
import com.brunomartin.cursomc.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Produto findById(Integer i) {
		return repository.findById(i).orElseThrow(() -> new ResourceNotFoundException("Produto nao encontrado"));
	}
	
	public List<Produto> findAll(){
		return repository.findAll();
	}
	
	public Produto create(Produto p) {
		return repository.save(p);
	}

	public Page<Produto> search(String nome, List<Integer> list , Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page,linesPerPage, Sort.Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(list);
		return repository.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
	}
	
}
