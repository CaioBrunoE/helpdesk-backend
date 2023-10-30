package com.caiobruno.helpdesk.domain.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import com.caiobruno.helpdesk.domain.Chamado;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;

public class ChamadoDto  implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;
		
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataAbertura = LocalDate.now();
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataFechamento;
    
	@NotNull(message = "O campo PRIORIDADE e requerido")
    private Integer prioridade;
	
	@NotNull(message = "O campo STATUS e requerido")
    private Integer status;
	
	@NotNull(message = "O campo TITULO e requerido")
    private String titulo;
	
	@NotNull(message = "O campo OBSERVAÇÕES e requerido")
    private String observacoe;
	
	@NotNull(message = "O campo TECNICO e requerido")
    private Integer tecnico;
	
	@NotNull(message = "O campo CLIENTE e requerido")
    private Integer cliente;
	
    private String nomeTecnico;
    
    private String nomeCliente;

	public ChamadoDto() {
		super();
		
	}

	public ChamadoDto(Chamado obj) {
		super();
		this.id = obj.getId();
		this.dataAbertura = obj.getDataAbertura();
		this.dataFechamento = obj.getDataFechamento();
		this.prioridade = obj.getPrioridade().getCodigo();
		this.status = obj.getStatus().getCodigo();
		this.titulo = obj.getTitulo();
		this.observacoe = obj.getObservacoe();
		this.tecnico = obj.getTecnico().getId();
		this.cliente = obj.getCliente().getId();
		this.nomeTecnico = obj.getTecnico().getNome();
		this.nomeCliente = obj.getCliente().getNome();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDate dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public LocalDate getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(LocalDate dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public Integer getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Integer prioridade) {
		this.prioridade = prioridade;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getObservacoe() {
		return observacoe;
	}

	public void setObservacoe(String observacoe) {
		this.observacoe = observacoe;
	}

	public Integer getTecnico() {
		return tecnico;
	}

	public void setTecnico(Integer tecnico) {
		this.tecnico = tecnico;
	}

	public Integer getCliente() {
		return cliente;
	}

	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}

	public String getNomeTecnico() {
		return nomeTecnico;
	}

	public void setNomeTecnico(String nomeTecnico) {
		this.nomeTecnico = nomeTecnico;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
    
    
}
