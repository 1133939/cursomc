package com.matheuscampelo.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.matheuscampelo.cursomc.model.Categoria;
import com.matheuscampelo.cursomc.repositories.CategoriaRepository;
import com.matheuscampelo.cursomc.services.exceptions.DataIntegrityException;
import com.matheuscampelo.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository repository;
	
	public Categoria find(Integer id) {
		Optional <Categoria> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado: "
		+id+
		", Tipo: "+ Categoria.class.getName()));
	}
	
	public Categoria insert(Categoria categoria) {
		categoria.setId(null);
		return repository.save(categoria);
	}
	public Categoria update(Categoria categoria) {
		find(categoria.getId());
		return repository.save(categoria);
	}
	public void delete(Integer id) {
		find(id);
		try {
		repository.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir Categoria que possui Produtos");
		}
	}
	public List<Categoria> findAll(){
		return repository.findAll();
	}
}
