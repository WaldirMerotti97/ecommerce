package br.com.itau.casadocodigo.ecommerceAPI.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "caracteristicas_produtos")
public class Caracteristica {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nome;
	private String valor;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "produto_id", referencedColumnName = "id")
	private Produto produto;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}
