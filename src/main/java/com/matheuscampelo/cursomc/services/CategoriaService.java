package com.matheuscampelo.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheuscampelo.cursomc.model.Categoria;
import com.matheuscampelo.cursomc.repositories.CategoriaRepository;
import com.matheuscampelo.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository repository;
	public Categoria buscar(Integer id) {
		Optional <Categoria> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado: "
		+id+
		", Tipo: "+ Categoria.class.getName()));
	}
}
