package br.com.drogaria.domain;

public class Fabricante {
	private Long codigo;
	private String descricao;

	public Long getCodigo() {//get, estou colocando permissão de leitura para o campo código. alguém pode oconsultar o valor dele.
		return codigo;
	}

	public void setCodigo(Long codigo) { //set, estou colocando a permissão de escrita para o campo código. alguém pode editar o conteúdo do código
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
