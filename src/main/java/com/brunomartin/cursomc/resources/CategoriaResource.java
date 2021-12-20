package com.brunomartin.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brunomartin.cursomc.domain.Categoria;
import com.brunomartin.cursomc.domain.Produto;
import com.brunomartin.cursomc.service.CategoriaService;
import com.brunomartin.cursomc.service.ProdutoService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService repository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {
		Categoria c = repository.buscar(id);		
		return ResponseEntity.ok().body(c);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Categoria> addProduct(){
		Categoria cat1 = repository.buscar(1);
		Produto p1 = produtoService.findById(1); 
		System.out.println(p1.getNome());
		cat1.getProdutos().add(p1);
		repository.create(cat1);
		return ResponseEntity.ok().body(cat1);
	}

}
