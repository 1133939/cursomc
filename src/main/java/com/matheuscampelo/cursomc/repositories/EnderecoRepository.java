package com.matheuscampelo.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matheuscampelo.cursomc.model.Endereco;
@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

}
