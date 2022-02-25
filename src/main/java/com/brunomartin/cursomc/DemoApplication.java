package com.brunomartin.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

//		List<Categoria> categories = categoriaRepository.findAll();
//
//		List<Categoria> informática = categories.stream()
//				.filter(c -> c.getNome().equals("Informática"))
//				.collect(Collectors.toList());
//
//		Optional<Categoria> first = categories.stream().findFirst();
//
//		System.out.println(first.get().getNome());
//
//		informática.forEach(c -> {
//			System.out.println(c.getNome());
//		});

		List<Cliente> clientes = clienteRepository.findAll();
		List<Cliente> collect = clientes.stream()
				.filter(c -> c.getEmail().equals("nubia@yahoo.com"))
				.collect(Collectors.toList());

		// CONSUMER E PREDICATE



//		for(int a = 0; a < clientes.size(); a++){
//			if(pessoa.getNascionaliade().equals("Brasil")) {
//				pessoas.add(pessoa.getIndex(a));
//			}else{
//				System.out.println("Email nao cadastrado");
//			}
//		}

		if(collect.size() > 0){
			System.out.println("Email encontrado");
		}

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
//		Produto p4 = new Produto(null, "Mesa de escritório", 300.00);
//		Produto p5 = new Produto(null,"Toalha", 50.00);
//		Produto p6 = new Produto(null, "Colcha", 200.00);
//		Produto p7 = new Produto(null, "TV true color", 1200.00);
//		Produto p8 = new Produto(null, "Roçadeira", 800.00);
//		Produto p9 = new Produto(null, "Abajour", 100.00);
//		Produto p10 = new Produto(null, "Pendente", 180.00);
//		Produto p11 = new Produto(null,"Shampoo", 90.00);
//
//		cat1.getProdutos().addAll(Arrays.asList(p1,p2));
//		cat2.getProdutos().addAll(Arrays.asList(p3,p4));
//
//		p1.getCategorias().addAll(Arrays.asList(cat1,cat2));
//		p2.getCategorias().addAll(Arrays.asList(cat1, cat2,cat4));
//		p3.getCategorias().addAll(Arrays.asList(cat1,cat4));
//		p4.getCategorias().addAll(Arrays.asList(cat2));
//		p5.getCategorias().addAll(Arrays.asList(cat3));
//		p6.getCategorias().addAll(Arrays.asList(cat3));
//		p7.getCategorias().addAll(Arrays.asList(cat4));
//		p8.getCategorias().addAll(Arrays.asList(cat5));
//		p9.getCategorias().addAll(Arrays.asList(cat1,cat6));
//		p10.getCategorias().addAll(Arrays.asList(cat1,cat6));
//		p11.getCategorias().addAll(Arrays.asList(cat1,cat7));
//
//		categoriaRepository.saveAll(Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6,cat7));
//		produtoRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8,p9, p10,p11));
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
