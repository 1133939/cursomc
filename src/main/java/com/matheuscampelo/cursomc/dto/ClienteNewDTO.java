package com.matheuscampelo.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.matheuscampelo.cursomc.services.validation.ClienteInsert;
@ClienteInsert
public class ClienteNewDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@NotEmpty(message="preenchimento obrigatório")
	@Length(min=5, max=50, message="deve conter entre 5 a 50 caracteres" )
	private String nome;
	
	@NotEmpty(message="preenchimento obrigatorio")
	@Email(message="email inválido")
	private String email;
	
	@NotEmpty(message="preenchimento obrigatorio")
	private String cpfOuCnpj;
	
	private Integer tipo;
	
	@NotEmpty(message="preenchimento obrigatorio")
	private String logradouro;
	
	@NotEmpty(message="preenchimento obrigatorio")
	private String numero;
	
	private String complemento;
	private String bairro;
	
	@NotEmpty(message="preenchimento obrigatorio")
	private String cep;
	
	@NotEmpty(message="preenchimento obrigatorio")
	private String telefone1;
	
	private String telefone2;
	private String telefone3;
	
	private Integer cidadeId;
	
	public ClienteNewDTO() {	
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public Integer getTipo() {
		return tipo;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public String getCep() {
		return cep;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public String getTelefone3() {
		return telefone3;
	}

	public Integer getCidadeId() {
		return cidadeId;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setCpfOuCpnj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public void setTelefone3(String telefone3) {
		this.telefone3 = telefone3;
	}

	public void setCidadeId(Integer cidadeId) {
		this.cidadeId = cidadeId;
	}

	public String getCpfOuCnpj() {
		return this.cpfOuCnpj;
	}
	
	
	
}
