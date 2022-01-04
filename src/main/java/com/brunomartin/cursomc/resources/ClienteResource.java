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

import com.brunomartin.cursomc.domain.Cliente;
import com.brunomartin.cursomc.dto.ClienteDTO;
import com.brunomartin.cursomc.dto.ClienteNewDTO;
import com.brunomartin.cursomc.service.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService repository;
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {
		Cliente c = repository.find(id);		
		return ResponseEntity.ok().body(c);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody ClienteDTO obj,  @PathVariable Integer id, UriComponentsBuilder ucb){
		Cliente categoria = repository.fromDTO(obj);
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
	public ResponseEntity<List<ClienteDTO>> findAll() {
		List<Cliente> categoria = repository.findAll();	
		
		// Convertendo os elementos da lista de Cliente acima para ClienteDTO. 
		List<ClienteDTO> listDto = categoria.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@GetMapping("/page")
	public ResponseEntity<Page<ClienteDTO>> findByPages(
			@RequestParam(value="page", defaultValue = "0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue = "nome") String orderBy, 
			@RequestParam(value="direction", defaultValue = "ASC") String direction) {
		
		Page<Cliente> categoria = repository.findPage(page,linesPerPage, orderBy,direction);	
		// Convertendo os elementos da lista de Cliente acima para ClienteDTO. 
		Page<ClienteDTO> listDto = categoria.map(obj -> new ClienteDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objDto, UriComponentsBuilder ucb){
		Cliente obj = repository.fromDTO(objDto);
		obj = repository.create(obj);
		URI uri = ucb.path("/categorias/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
}
