package com.matheuscampelo.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matheuscampelo.cursomc.model.Cidade;
@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

}
