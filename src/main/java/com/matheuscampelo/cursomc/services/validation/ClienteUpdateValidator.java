package com.matheuscampelo.cursomc.services.validation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.matheuscampelo.cursomc.dto.ClienteDTO;
import com.matheuscampelo.cursomc.dto.ClienteNewDTO;
import com.matheuscampelo.cursomc.model.Cliente;
import com.matheuscampelo.cursomc.repositories.ClienteRepository;
import com.matheuscampelo.cursomc.resources.exceptions.FieldMessage;



public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {
	@Autowired
	private ClienteRepository repoCliente;
	@Autowired
	private HttpServletRequest request;
	@Override
	public void initialize(ClienteUpdate ann) {
	}

	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();
		Map<String, String> map = (Map<String, String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
		Cliente aux = repoCliente.findByEmail(objDto.getEmail());
		if(aux != null && aux.getId() != uriId) {
			list.add(new FieldMessage("email", "Email já cadastrado"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
