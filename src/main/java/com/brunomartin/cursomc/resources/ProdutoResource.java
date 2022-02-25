package com.brunomartin.cursomc.resources;

import antlr.Utils;
import com.brunomartin.cursomc.domain.Categoria;
import com.brunomartin.cursomc.domain.Pedido;
import com.brunomartin.cursomc.domain.Produto;
import com.brunomartin.cursomc.dto.CategoriaDTO;
import com.brunomartin.cursomc.dto.ProdutoDTO;
import com.brunomartin.cursomc.repository.Utils.URL;
import com.brunomartin.cursomc.service.PedidoService;
import com.brunomartin.cursomc.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService repository;
	
		
	@GetMapping("/{id}")
	public ResponseEntity<Produto> find(@PathVariable Integer id) {
		Produto c = repository.findById(id);
		return ResponseEntity.ok().body(c);
	}

	@GetMapping
	public ResponseEntity<Page<ProdutoDTO>> findByPages(
			@RequestParam(value="nome", defaultValue = "") String nome,
			@RequestParam(value="categorias", defaultValue = "") String categorias,
			@RequestParam(value="page", defaultValue = "0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value="direction", defaultValue = "ASC") String direction) {

		String nomeDecoded = URL.decodeString(nome);
		List<Integer> list = URL.decodeIntList(categorias);
		Page<Produto> produto = repository.search(nome, list,page,linesPerPage, orderBy,direction);
		Page<ProdutoDTO> listDto = produto.map(record -> new ProdutoDTO(record));

		return ResponseEntity.ok().body(listDto);
	}
}
