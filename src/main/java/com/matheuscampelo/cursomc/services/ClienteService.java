package com.matheuscampelo.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.matheuscampelo.cursomc.dto.ClienteDTO;
import com.matheuscampelo.cursomc.model.Cliente;
import com.matheuscampelo.cursomc.repositories.ClienteRepository;
import com.matheuscampelo.cursomc.services.exceptions.DataIntegrityException;
import com.matheuscampelo.cursomc.services.exceptions.ObjectNotFoundException;


@Service
public class ClienteService {
	
@Autowired
private ClienteRepository repository;

public Cliente find(Integer id){
Optional <Cliente> cliente= repository.findById(id);
return cliente.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado com Id: "
+ id +", Tipo: " + Cliente.class.getName() ));
}

public Cliente update(Cliente cliente) {
	Cliente newObj = find(cliente.getId());
	updateData(newObj, cliente);
	return repository.save(newObj);
}
public void delete(Integer id) {
	find(id);
	try {
	repository.deleteById(id);
	}catch (DataIntegrityViolationException e) {
		throw new DataIntegrityException("Não é possivel excluir Cliente que possui Pedidos");
	}
}
public List<Cliente> findAll(){
	return repository.findAll();
}
public Page<Cliente> findPage(Integer page, Integer size, String orderBy, String direction){
	PageRequest pageRequest = PageRequest.of(page, size, Direction.valueOf(direction), orderBy);
	return repository.findAll(pageRequest);
	
}
public Cliente fromDTO(ClienteDTO clienteDTO) {
	return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(),null,null);
}
private void updateData(Cliente newObj, Cliente cliente) {
	newObj.setNome(cliente.getNome());
	newObj.setEmail(cliente.getEmail());
}
}
