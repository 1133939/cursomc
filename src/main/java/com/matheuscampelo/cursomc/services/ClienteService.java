package com.matheuscampelo.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheuscampelo.cursomc.model.Cliente;
import com.matheuscampelo.cursomc.repositories.ClienteRepository;
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
}
