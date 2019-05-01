package com.matheuscampelo.cursomc.model.enums;

public enum Perfil {
ADMIN(1, "ROLE_ADMIN"),
CLIENTE(2, "ROLE_CLIENTE");
	
	private int cod;
	private String descricao;
	
	private Perfil(int cod, String descricao) {
		this.cod=cod;
		this.descricao=descricao;
	}
	
	public int getCod() {
		return this.cod;
	}
	
	public String getDescricao() {
	return this.descricao;
	}
	
	public static Perfil toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		for(Perfil estado : Perfil.values()) {
			if(cod.equals(estado.getCod())) {
				return estado;
			}
		}
		throw new IllegalArgumentException("Id inválido	"+ cod);
	}
	}

