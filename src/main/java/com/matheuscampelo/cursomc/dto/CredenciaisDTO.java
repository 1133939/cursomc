package com.matheuscampelo.cursomc.dto;

import java.io.Serializable;

public class CredenciaisDTO implements Serializable{

	private static final long serialVersionUID = 1L;
private String email;
private String senha;


public CredenciaisDTO() {
}

public String getEmail() {
	return email;
}
public String getSenha() {
	return senha;
}
public void setEmail(String email) {
	this.email = email;
}
public void setSenha(String senha) {
	this.senha = senha;
}

}
