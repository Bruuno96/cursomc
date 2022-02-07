package com.brunomartin.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.brunomartin.cursomc.domain.Categoria;
import com.brunomartin.cursomc.domain.Cidade;
import com.brunomartin.cursomc.domain.Cliente;
import com.brunomartin.cursomc.domain.Endereco;
import com.brunomartin.cursomc.domain.Estado;
import com.brunomartin.cursomc.domain.ItemPedido;
import com.brunomartin.cursomc.domain.Pagamento;
import com.brunomartin.cursomc.domain.PagamentoComBoleto;
import com.brunomartin.cursomc.domain.PagamentoComCartao;
import com.brunomartin.cursomc.domain.Pedido;
import com.brunomartin.cursomc.domain.Produto;
import com.brunomartin.cursomc.domain.enums.EstadoPagamento;
import com.brunomartin.cursomc.domain.enums.TipoCliente;
import com.brunomartin.cursomc.repository.CategoriaRepository;
import com.brunomartin.cursomc.repository.CidadeRepository;
import com.brunomartin.cursomc.repository.ClienteRepository;
import com.brunomartin.cursomc.repository.EnderecoRepository;
import com.brunomartin.cursomc.repository.EstadoRepository;
import com.brunomartin.cursomc.repository.ItemPedidoRepository;
import com.brunomartin.cursomc.repository.PagamentoRepository;
import com.brunomartin.cursomc.repository.PedidoRepository;
import com.brunomartin.cursomc.repository.ProdutoRepository;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		Categoria cat1 = new Categoria(null,"Informática");
//		Categoria cat2 = new Categoria(null,"Escritório");
//		Categoria cat3 = new Categoria(null,"Pet");
//		Categoria cat4 = new Categoria(null,"Cama mesa e banho");
//		Categoria cat5 = new Categoria(null,"Eletrônicos");
//		Categoria cat6 = new Categoria(null,"Jardinagem");
//		Categoria cat7 = new Categoria(null,"Decoração");
//		Categoria cat8 = new Categoria(null,"Perfumaria");
//		
//		
//		
//		Produto p1 = new Produto(null, "Computador", 2000.00);
//		Produto p2 = new Produto(null, "Impressora", 800.00);
//		Produto p3 = new Produto(null, "Computador", 80.00);
//		
//		cat1.getProdutos().addAll(Arrays.asList(p1,p2));
//		cat2.getProdutos().addAll(Arrays.asList(p3));
//		
//		p1.getCategorias().addAll(Arrays.asList(cat1));
//		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
//		p3.getCategorias().addAll(Arrays.asList(cat1));
//			
//		
//		categoriaRepository.saveAll(Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6,cat7));
//		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
//		
//		Estado est1 = new Estado(null,"Minas Gerais");
//		Estado est2 = new Estado(null, "São Paulo");
//		
//		Cidade c1 =  new Cidade(null,"Uberlândia", est1);
//		Cidade c2 = new Cidade(null,"São Paulo", est2);
//		Cidade c3 = new Cidade(null,"Campinas", est2);
//		
//		est1.getCidades().addAll(Arrays.asList(c1));
//		est2.getCidades().addAll(Arrays.asList(c2,c3));
//		
//		estadoRepository.saveAll(Arrays.asList(est1,est2));
//		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
//		
//		Cliente cli1 = new Cliente(null,"Maria Silva", "3678912377","maria@gmail.com", TipoCliente.PESSOAFISICA);
//		
//		cli1.getTelefones().addAll(Arrays.asList("272363323","93838393"));
//		
//		Endereco e1 = new Endereco(null,"Rua flores","300","Apto 203","Jardim","382208434",cli1,c1);
//		Endereco e2 = new Endereco(null,"Avenida Matos","105","Sala 800","Centro","382208434",cli1,c2);
//		
//		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
//		
//		clienteRepository.save(cli1);
//		enderecoRepository.saveAll(Arrays.asList(e1,e2));
//		
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy HH:mm");
//		
//		Pedido ped1 = new Pedido(null,sdf.parse("30/09/2021 10:32"),cli1,e1);
//		Pedido ped2 = new Pedido(null,sdf.parse("10/10/2021 19:35"),cli1,e2);
//		
//		Pagamento pagto1 = new PagamentoComCartao(null,EstadoPagamento.QUITADO,ped1,6);
//		ped1.setPagamento(pagto1);
//		
//		Pagamento pagto2 = new PagamentoComBoleto(null,EstadoPagamento.PENDENTE,ped2,sdf.parse("20/10/2021 00:00"),null);
//		ped2.setPagamento(pagto2);
//		
//		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
//		
//		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
//		pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));
//		
//		// Cria itens do pedido
//		ItemPedido ip1 = new ItemPedido(ped1,p1,0.00,1,p1.getPreco());
//		ItemPedido ip2 = new ItemPedido(ped1,p3,0.00,2,p3.getPreco());
//		ItemPedido ip3 = new ItemPedido(ped2,p2,0.00,1,p2.getPreco());
//		
//		// Relaciona cada item ao respectivo pedido (Pedido possui N itens)
//		p1.getItens().addAll(Arrays.asList(ip1));
//		p2.getItens().addAll(Arrays.asList(ip3));
//		p3.getItens().addAll(Arrays.asList(ip2));
//		
//		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
		
		
	}

}
