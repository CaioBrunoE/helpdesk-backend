package com.caiobruno.helpdesk.domain.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.caiobruno.helpdesk.domain.Tecnico;
import com.caiobruno.helpdesk.domain.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;

public class TecnicoDto implements Serializable {
	private static final long serialVersionUID = 1L;

	protected Integer id;
	@NotNull(message = "O campo NOME e requerido")
	protected String nome;
	@NotNull(message = "O campo CPF e requerido")
	protected String cpf;
	@NotNull(message = "O campo EMAIL e requerido")
	protected String email;
	@NotNull(message = "O campo SENHA e requerido")
	protected String senha;
	protected  Set<Integer> perfils = new HashSet<>();

	@JsonFormat(pattern = "dd/MM/yyyy")	
	protected LocalDate dataCriacao = LocalDate.now();

	public TecnicoDto() {
		super();
		this.perfils.add(2);
	}

	public TecnicoDto(Tecnico obj) {
		super();
		this.id = obj.getId();
		this.nome =obj.getNome();
		this.cpf = obj.getCpf();
		this.email = obj.getEmail();
		this.senha = obj.getSenha();
		this.perfils = obj.getPerfils().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
		this.dataCriacao = obj.getDataCriacao();
	    this.perfils.add(2);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Set<Perfil> getPerfils() {
		return perfils.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}

	public void setPerfils(Perfil perfils) {
		this.perfils.add(perfils.getCodigo());
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	
}
