package com.rodrigo.testevenda.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rodrigo.testevenda.domain.enums.LocalVenda;
import com.rodrigo.testevenda.dtos.VendaDTO;

@Entity
@Table(name = "tb_venda")
public class Venda implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String titulo;
	private String autor;
	private String codigo;
	private Double preco;
	private String descricao;
	
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataVenda = LocalDate.now();
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "LOCAL_VENDA")
	private Set<Integer> localVenda = new HashSet<>();
	
	public Venda() {
		super();
		addLocal(LocalVenda.LOJA);
	}

	public Venda(Integer id, String titulo,String autor, String codigo, Double preco,String descricao, LocalDate dataVenda) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.codigo = codigo;
		this.preco = preco;
		this.descricao = descricao;
		this.dataVenda = dataVenda;
		addLocal(LocalVenda.LOJA);
	}
	
	public Venda(VendaDTO obj) {
		super();
		this.id = obj.getId();
		this.titulo = obj.getTitulo();
		this.autor = obj.getAutor();
		this.codigo = obj.getCodigo();
		this.preco = obj.getPreco();
		this.descricao = obj.getDescricao();
		this.localVenda = obj.getLocalVenda().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
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

	
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Venda other = (Venda) obj;
		return Objects.equals(id, other.id);
	}
	
	


}
