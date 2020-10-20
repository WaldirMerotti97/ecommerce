package br.com.itau.casadocodigo.ecommerceAPI.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "imagem")
public class Imagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String link;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "produto_id", referencedColumnName = "id")
	private Produto produto;

	public Imagem(String link, Produto produto) {
		this.link = link;
		this.produto = produto;
	}

	@Deprecated
	public Imagem() {

	}

	public Imagem(Produto produto, String link) {
		this.produto = produto;
		this.link = link;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}
