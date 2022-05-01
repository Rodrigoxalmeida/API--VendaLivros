package com.rodrigo.testevenda.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rodrigo.testevenda.domain.Venda;
import com.rodrigo.testevenda.domain.enums.LocalVenda;

public class VendaDTO implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@NotNull(message = "O campo titulo é requerido" )
	private String titulo;
	private String autor;
	@NotNull(message = "O campo codigo é requerido")
	private String codigo;
	private Double preco;
	private String descricao;
	
	private Set<Integer> localVenda = new HashSet<>();
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataVenda = LocalDate.now();
	
	public VendaDTO() {
		super();
		addLocal(LocalVenda.LOJA);
	}
	
	public VendaDTO(Venda venda) {
		super();
		this.id = venda.getId();
		this.titulo = venda.getTitulo();
		this.autor = venda.getAutor();
		this.codigo = venda.getCodigo();
		this.preco = venda.getPreco();
		this.descricao = venda.getDescricao();
		this.localVenda = venda.getLocalVenda().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
		addLocal(LocalVenda.LOJA);
	}	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(LocalDate dataVenda) {
		this.dataVenda = dataVenda;
	}	

	public Set<LocalVenda> getLocalVenda() {
		return localVenda.stream().map(x -> LocalVenda.toEnum(x)).collect(Collectors.toSet());
	}

	public void addLocal(LocalVenda localVenda) {
		this.localVenda.add(localVenda.getCodigo());
	}	

}
