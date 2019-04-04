package com.matheuscampelo.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.matheuscampelo.cursomc.model.Cliente;
import com.matheuscampelo.cursomc.services.validation.ClienteUpdate;
@ClienteUpdate
public class ClienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	@NotEmpty(message="preenchimento obrigatorio")
	@Length(min=5, max=60, message="tamanho tem que ser entre 5 e 60 caracteres")
	private String nome;
	@NotEmpty(message="preenchimento obrigatorio")
	@Email(message="email inválido")
	private String email;
	
	public ClienteDTO() {
		
	}
	public ClienteDTO(Cliente cliente) {
	id = cliente.getId();
	nome = cliente.getNome();
	email = cliente.getEmail();
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

}
