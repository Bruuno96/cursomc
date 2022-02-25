package com.brunomartin.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.brunomartin.cursomc.domain.Categoria;
import com.brunomartin.cursomc.dto.CategoriaDTO;
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
		Categoria c = repository.find(id);		
		return ResponseEntity.ok().body(c);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO objDto, UriComponentsBuilder ucb){
		Categoria obj = repository.fromDTO(objDto);
		obj = repository.create(obj);
		URI uri = ucb.path("/categorias/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	 
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody CategoriaDTO obj,  @PathVariable Integer id, UriComponentsBuilder ucb){
		Categoria categoria = repository.fromDTO(obj);
		categoria.setId(id);
		repository.update(categoria);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		repository.delete(id);		
		return ResponseEntity.noContent().build();
	}


	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> findAll() {
		List<Categoria> categoria = repository.findAll();	
		
		// Convertendo os elementos da lista de Categoria acima para CategoriaDTO. 
		List<CategoriaDTO> listDto = categoria.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@GetMapping("/page")
	public ResponseEntity<Page<CategoriaDTO>> findByPages(
			@RequestParam(value="page", defaultValue = "0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue = "nome") String orderBy, 
			@RequestParam(value="direction", defaultValue = "ASC") String direction) {
		
		Page<Categoria> categoria = repository.findPage(page,linesPerPage, orderBy,direction);	
		// Convertendo os elementos da lista de Categoria acima para CategoriaDTO. 
		Page<CategoriaDTO> listDto = categoria.map(obj -> new CategoriaDTO(obj));

		return ResponseEntity.ok().body(listDto);
	}

}
