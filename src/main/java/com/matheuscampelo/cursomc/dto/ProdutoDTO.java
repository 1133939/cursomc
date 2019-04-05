package com.matheuscampelo.cursomc.dto;

import java.io.Serializable;

import com.matheuscampelo.cursomc.model.Produto;

public class ProdutoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private Double preco;
	
	public ProdutoDTO() {
		
	}
	public ProdutoDTO(Produto produto) {
	id = produto.getId();
	nome = produto.getNome();
	preco = produto.getPreco();
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
}
