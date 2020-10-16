package br.com.itau.casadocodigo.ecommerceAPI.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "categoria")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int idCategoriaMae;
	private String nome;
	@OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
	private List<Produto> produtos;

	@Deprecated
	public Categoria() {

	}

	public Categoria(String nome, int categoriaMae) {
		this.nome = nome;
		this.idCategoriaMae = categoriaMae;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdCategoriaMae() {
		return idCategoriaMae;
	}

	public void setIdCategoriaMae(int idCategoriaMae) {
		this.idCategoriaMae = idCategoriaMae;
	}

}
