package com.matheuscampelo.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matheuscampelo.cursomc.model.Produto;
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
	