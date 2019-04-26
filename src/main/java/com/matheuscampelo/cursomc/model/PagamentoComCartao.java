package com.matheuscampelo.cursomc.model;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.matheuscampelo.cursomc.model.enums.EstadoPagamento;

@Entity
@JsonTypeName("pagamentoComCartao")
public class PagamentoComCartao extends Pagamento {
private static final long serialVersionUID = 1L;	
private Integer numeroDeParcelas;

public PagamentoComCartao() {
	
}

public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroDeParcelas) {
	super(id, estado, pedido);
	this.numeroDeParcelas=numeroDeParcelas;
}

public Integer getNumeroDeParcelas() {
	return numeroDeParcelas;
}

public void setNumeroDeParcelas(Integer numeroDeParcelas) {
	this.numeroDeParcelas = numeroDeParcelas;
}

}
