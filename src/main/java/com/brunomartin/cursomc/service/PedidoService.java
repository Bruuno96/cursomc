package com.brunomartin.cursomc.service;

import com.brunomartin.cursomc.domain.ItemPedido;
import com.brunomartin.cursomc.domain.PagamentoComBoleto;
import com.brunomartin.cursomc.domain.Pedido;
import com.brunomartin.cursomc.domain.enums.EstadoPagamento;
import com.brunomartin.cursomc.exceptions.ObjectNotFoundException;
import com.brunomartin.cursomc.repository.ItemPedidoRepository;
import com.brunomartin.cursomc.repository.PagamentoRepository;
import com.brunomartin.cursomc.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;

	@Autowired
	private BoletoService boletoService;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public Pedido buscar(Integer id) {
		return repository.findById(id).orElseThrow(() -> 
					new ObjectNotFoundException("Objeto nao encontrado: "+id+" Tipo: "+Pedido.class.getName()));
	}

	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		obj = repository.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.findById(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		return obj;
	}
}
