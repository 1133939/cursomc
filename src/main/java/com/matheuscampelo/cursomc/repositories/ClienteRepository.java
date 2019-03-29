package com.matheuscampelo.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matheuscampelo.cursomc.model.Cliente;
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
