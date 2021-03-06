package com.brunomartin.cursomc.service.validation;

import com.brunomartin.cursomc.domain.Cliente;
import com.brunomartin.cursomc.domain.enums.TipoCliente;
import com.brunomartin.cursomc.dto.ClienteDTO;
import com.brunomartin.cursomc.dto.ClienteNewDTO;
import com.brunomartin.cursomc.repository.ClienteRepository;
import com.brunomartin.cursomc.resources.exceptions.FieldMessage;
import com.brunomartin.cursomc.service.validation.Utils.BR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private HttpServletRequest request;
	
	
	@Override
	 public void initialize(ClienteUpdate ann) {
	 }
	 
	 @Override
	 public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer id = Integer.parseInt(map.get("id"));

		List<FieldMessage> list = new ArrayList<>();

		 Cliente findByEmail = clienteRepository.findByEmail(objDto.getEmail());
		 	if(findByEmail.getEmail() != null && !findByEmail.getId().equals(id)) {
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