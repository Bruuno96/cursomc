package com.brunomartin.cursomc.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

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
	
	public ResponseEntity<Void> insert(Categoria obj, UriComponentsBuilder ucb){
		obj = repository.create(obj);
		URI uri = ucb.path("/categorias/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PostMapping("/add")
	public ResponseEntity<Categoria> addProduct(){
		Categoria cat1 = repository.buscar(1);
		Produto p1 = produtoService.findById(1); 
		cat1.getProdutos().add(p1);
		repository.create(cat1);
		produtoService.create(p1);
		return ResponseEntity.ok().build();
	}

}
