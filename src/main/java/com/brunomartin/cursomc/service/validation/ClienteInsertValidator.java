package com.brunomartin.cursomc.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.brunomartin.cursomc.domain.Cliente;
import com.brunomartin.cursomc.domain.enums.TipoCliente;
import com.brunomartin.cursomc.dto.ClienteNewDTO;
import com.brunomartin.cursomc.repository.ClienteRepository;
import com.brunomartin.cursomc.resources.exceptions.FieldMessage;
import com.brunomartin.cursomc.service.validation.Utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	
	@Override
	 public void initialize(ClienteInsert ann) {
	 }
	 
	 @Override
	 public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		 List<FieldMessage> list = new ArrayList<>();
		 
		 if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCode()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			 list.add(new FieldMessage("cpfOuCnpj","CPF inválido"));
		 }
		 
		 if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCode()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
			 list.add(new FieldMessage("cpfOuCnpj","CNPJ inválido"));
		 }

		 Cliente findByEmail = clienteRepository.findByEmail(objDto.getEmail());
		 	if(findByEmail.getEmail() != null) {
		 		list.add(new FieldMessage("email", "Email já existente"));
		 	}
		 for (FieldMessage e : list) {
		 context.disableDefaultConstraintViolation();
		 context.buildConstraintViolationWithTemplate(e.getMessage())
		 .addPropertyNode(e.getFieldName()).addConstraintViolation();
		 }
		 return list.isEmpty();
	 }
	
	}