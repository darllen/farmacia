package br.com.drogaria.domain;

public class Fabricante {
	private Long codigo;
	private String descricao;

	public Long getCodigo() {//get, estou colocando permiss�o de leitura para o campo c�digo. algu�m pode oconsultar o valor dele.
		return codigo;
	}

	public void setCodigo(Long codigo) { //set, estou colocando a permiss�o de escrita para o campo c�digo. algu�m pode editar o conte�do do c�digo
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		String saida = codigo + " - " + descricao;
		return saida;
	}
}
