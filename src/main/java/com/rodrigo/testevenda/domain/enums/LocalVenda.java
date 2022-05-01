package com.rodrigo.testevenda.domain.enums;

public enum LocalVenda {
	
	LOJA(0, "ROLE_LOJA"), INSTAGRAM(1, "ROLE_INSTAGRAM"), WHATSAPP(2, "ROLE_WHATSAAP");
	
	private Integer codigo;
	private String descricao;
	
	private LocalVenda(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static LocalVenda toEnum(Integer codigo) {
		if(codigo == null) {
			return null;
		}
		
		for(LocalVenda x : LocalVenda.values()) {
			if(codigo.equals(x.getCodigo())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Local de venda inválido");
	}
	
	
}
