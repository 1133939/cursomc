package com.matheuscampelo.cursomc.model.enums;

public enum EstadoPagamento {
PENDENTE(1, "Pendente"),
QUITADO(2, "Quitado"), 
CANCELADO(3, "Cancelado");
	
	private int cod;
	private String descricao;
	
	private EstadoPagamento(int cod, String descricao) {
		this.cod=cod;
		this.descricao=descricao;
	}
	
	public int getCod() {
		return this.cod;
	}
	
	public String descricao() {
	return this.descricao;
	}
	
	public static EstadoPagamento toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		for(EstadoPagamento estado : EstadoPagamento.values()) {
			if(cod.equals(estado.getCod())) {
				return estado;
			}
		}
		throw new IllegalArgumentException("Id inválido	"+ cod);
	}
	}

