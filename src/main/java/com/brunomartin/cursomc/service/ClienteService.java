package com.brunomartin.cursomc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.brunomartin.cursomc.domain.Cidade;
import com.brunomartin.cursomc.domain.Cliente;
import com.brunomartin.cursomc.domain.Endereco;
import com.brunomartin.cursomc.domain.enums.TipoCliente;
import com.brunomartin.cursomc.dto.ClienteDTO;
import com.brunomartin.cursomc.dto.ClienteNewDTO;
import com.brunomartin.cursomc.exceptions.DataIntegrityException;
import com.brunomartin.cursomc.exceptions.ObjectNotFoundException;
import com.brunomartin.cursomc.repository.ClienteRepository;
import com.brunomartin.cursomc.repository.EnderecoRepository;



@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private EnderecoRepository endRepository;
	
	public Cliente find(Integer id) {
		return repository.findById(id).orElseThrow(() -> 
					new ObjectNotFoundException("Objeto nao encontrado: "+id+" Tipo: "+Cliente.class.getName()));
	}
	
	public Cliente create(Cliente c) {
		c = repository.save(c);
		endRepository.saveAll(c.getEnderecos());
		return c;
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj,obj);
		return repository.save(newObj);
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

	public void delete(Integer id) {
		Cliente c = find(id);
		try {
			repository.delete(c);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir um cliente que possui produtos");
		}
	}

	public List<Cliente> findAll() {
		return repository.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page,linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO dto) {
		return new Cliente(dto.getId(),dto.getNome(),null,dto.getEmail(),null);
	}
	
	public Cliente fromDTO(ClienteNewDTO dto) {
		Cliente cli = new Cliente(null,dto.getNome(),dto.getCpfOuCnpj(),dto.getEmail(),TipoCliente.toEnum(dto.getTipo()));
		Cidade cid = new Cidade(dto.getCidadeId(),null,null);
		Endereco endereco = new Endereco(null,dto.getLogradouro(),dto.getNumero(),dto.getComplemento(),dto.getBairro(),dto.getCep(),cli,cid);
		cli.getEnderecos().add(endereco);
		cli.getTelefones().add(dto.getTelefone1());
		
		if(dto.getTelefone2() != null) {
			cli.getTelefones().add(dto.getTelefone2());
		} 
		if(dto.getTelefone3() != null) {
			cli.getTelefones().add(dto.getTelefone3());
		} 
		
		return cli;
		}
	
	
	
	
}
