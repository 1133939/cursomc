package com.matheuscampelo.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.matheuscampelo.cursomc.model.Categoria;
import com.matheuscampelo.cursomc.model.Produto;
import com.matheuscampelo.cursomc.repositories.CategoriaRepository;
import com.matheuscampelo.cursomc.repositories.ProdutoRepository;
import com.matheuscampelo.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {
@Autowired
private CategoriaRepository repositoryCat;
@Autowired
private ProdutoRepository repository;

public Produto find(Integer id) {
	Optional<Produto> pedido = repository.findById(id);
	return pedido.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado com Id: "+
	id+" Tipo: "+Produto.class.getName()));
}
public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer size, String orderBy, String direction){
	PageRequest pageRequest = PageRequest.of(page, size, Direction.valueOf(direction), orderBy);
	List <Categoria> categorias = repositoryCat.findAllById(ids);
	return repository.search(nome,categorias, pageRequest);
}
}
