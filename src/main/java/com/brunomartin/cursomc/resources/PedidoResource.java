package com.brunomartin.cursomc.resources;

import com.brunomartin.cursomc.domain.Categoria;
import com.brunomartin.cursomc.dto.CategoriaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.brunomartin.cursomc.domain.Pedido;
import com.brunomartin.cursomc.service.PedidoService;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoResource {
	
	@Autowired
	private PedidoService repository;
	
		
	@GetMapping("/{id}")
	public ResponseEntity<Pedido> find(@PathVariable Integer id) {
		Pedido c = repository.buscar(id);		
		return ResponseEntity.ok().body(c);
	}

	@PostMapping
	public ResponseEntity<Pedido> insert(@Valid @RequestBody Pedido pedido, UriComponentsBuilder ucb){
		Pedido created = repository.insert(pedido);
		URI uri = ucb.path("/categorias/{id}").buildAndExpand(created.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

}
