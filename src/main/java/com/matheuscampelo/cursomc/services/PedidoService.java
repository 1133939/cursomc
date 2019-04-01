package com.matheuscampelo.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheuscampelo.cursomc.model.Pedido;
import com.matheuscampelo.cursomc.repositories.PedidoRepository;
import com.matheuscampelo.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
@Autowired
private PedidoRepository repository;

public Pedido buscar(Integer id) {
	Optional<Pedido> pedido = repository.findById(id);
	return pedido.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado com Id: "+
	id+" Tipo: "+Pedido.class.getName()));
}
}
