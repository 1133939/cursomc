package com.matheuscampelo.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.matheuscampelo.cursomc.dto.ClienteDTO;
import com.matheuscampelo.cursomc.dto.ClienteNewDTO;
import com.matheuscampelo.cursomc.model.Cliente;
import com.matheuscampelo.cursomc.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteResources {
	
	@Autowired
	private ClienteService service;
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO clienteDTO){
		Cliente cliente = service.fromDTO(clienteDTO);
		cliente = service.insert(cliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
@RequestMapping(method=RequestMethod.GET, value="/{id}")
public ResponseEntity<?> find(@PathVariable Integer id) {
	Cliente cliente = service.find(id);
	return ResponseEntity.ok().body(cliente);
}
@RequestMapping(value="/{id}", method=RequestMethod.PUT)
public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO clienteDTO, @PathVariable Integer id){
	Cliente cliente = service.fromDTO(clienteDTO);
	cliente.setId(id);
	cliente = service.update(cliente);
	return ResponseEntity.noContent().build();
}
@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
public ResponseEntity<Void> delete(@PathVariable Integer id){
	service.delete(id);
	return ResponseEntity.noContent().build();
}
@RequestMapping(method=RequestMethod.GET)
public ResponseEntity<List<ClienteDTO>> findAll(){
	List<Cliente> clientes = service.findAll();
	List<ClienteDTO> clientesDTO = clientes.stream()
			.map(cliente -> new ClienteDTO(cliente)).collect(Collectors.toList());
	return ResponseEntity.ok().body(clientesDTO);
}
@RequestMapping(value="page",method=RequestMethod.GET)
public ResponseEntity<Page<ClienteDTO>> findPage(
		@RequestParam(value="page", defaultValue="0") Integer page, 
		@RequestParam(value="size", defaultValue="24") Integer size, 
		@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
		@RequestParam(value="direction", defaultValue="ASC") String direction){
	Page<Cliente> listPageCliente = service.findPage(page, size, orderBy, direction);
	Page<ClienteDTO> listPageClienteDTO = listPageCliente.map(varPage -> new ClienteDTO(varPage));
	return ResponseEntity.ok().body(listPageClienteDTO);
}
}
