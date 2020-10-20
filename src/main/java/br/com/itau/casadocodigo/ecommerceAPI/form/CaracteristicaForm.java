package br.com.itau.casadocodigo.ecommerceAPI.form;

import javax.validation.constraints.NotBlank;

import br.com.itau.casadocodigo.ecommerceAPI.model.Caracteristica;
import br.com.itau.casadocodigo.ecommerceAPI.model.Produto;

public class CaracteristicaForm {

	@NotBlank
	private String nome;
	@NotBlank
	private String descricao;

	public CaracteristicaForm(@NotBlank String nome, @NotBlank String descricao) {

		this.nome = nome;
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "CaracteristicaForm [nome=" + nome + ", descricao=" + descricao + "]";
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Caracteristica converter(Produto produto) {

		return new Caracteristica(nome, descricao, produto);
	}

}
