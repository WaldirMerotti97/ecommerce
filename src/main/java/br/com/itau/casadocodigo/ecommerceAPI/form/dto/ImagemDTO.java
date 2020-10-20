package br.com.itau.casadocodigo.ecommerceAPI.form.dto;

public class ImagemDTO {

	private int id;
	private String nome;
	private String link;

	public ImagemDTO(int id, String nome, String link) {
		this.id = id;
		this.nome = nome;
		this.link = link;
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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

}
