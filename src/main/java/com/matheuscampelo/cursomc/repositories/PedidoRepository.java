package com.matheuscampelo.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matheuscampelo.cursomc.model.Pedido;
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer>{

}
