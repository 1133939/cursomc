package com.matheuscampelo.cursomc.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.matheuscampelo.cursomc.model.Pedido;
import com.matheuscampelo.cursomc.services.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoResources {
	
@Autowired
private PedidoService service;

@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Pedido pedido = service.find(id);
		return ResponseEntity.ok().body(pedido);
	}
@RequestMapping(method=RequestMethod.POST)
public ResponseEntity<Void> inserir(@Valid @RequestBody Pedido pedido){
	pedido = service.insert(pedido);
	URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pedido.getId()).toUri();
	return ResponseEntity.created(uri).build();
	
}
}
