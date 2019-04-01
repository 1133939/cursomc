package com.matheuscampelo.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.matheuscampelo.cursomc.model.Cliente;
import com.matheuscampelo.cursomc.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteResources {
	
	@Autowired
	private ClienteService service;

@RequestMapping(method=RequestMethod.GET, value="/{id}")
public ResponseEntity<?> find(@PathVariable Integer id) {
	Cliente cliente = service.find(id);
	return ResponseEntity.ok().body(cliente);
}
}
