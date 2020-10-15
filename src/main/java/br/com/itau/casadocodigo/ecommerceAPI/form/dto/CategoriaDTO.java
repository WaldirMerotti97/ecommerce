package br.com.itau.casadocodigo.ecommerceAPI.form.dto;

public class CategoriaDTO {

	private int id;

	private String nome;

	private String categoriaMae;

	public CategoriaDTO(int id, String nome, String categoriaMae) {
		this.id = id;
		this.nome = nome;
		this.categoriaMae = categoriaMae;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCategoriaMae() {
		return categoriaMae;
	}

	public void setCategoriaMae(String categoriaMae) {
		this.categoriaMae = categoriaMae;
	}

}
