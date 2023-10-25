package com.caiobruno.helpdesk.domain;

import java.util.ArrayList;
import java.util.List;

import com.caiobruno.helpdesk.domain.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;


@Entity
public class Cliente  extends Pessoa  {
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	private List<Chamado> chamados = new ArrayList<>();

	public Cliente() {
		super();
		setPerfils(Perfil.CLIENTE);
	}

	public Cliente(Integer id, String nome, String cpf, String email, String senha ) {
		super(id, nome, cpf, email, senha);
		setPerfils(Perfil.CLIENTE);
	}

	public List<Chamado> getChamados() {
		return chamados;
	}

	public void setChamados(List<Chamado> chamados) {
		this.chamados = chamados;
	}

}
