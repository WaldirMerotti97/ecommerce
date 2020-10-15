package br.com.itau.casadocodigo.ecommerceAPI.form;

import javax.validation.constraints.NotBlank;

import br.com.itau.casadocodigo.ecommerceAPI.config.validacao.UniqueValue;
import br.com.itau.casadocodigo.ecommerceAPI.model.Categoria;

public class CategoriaForm {

	@NotBlank
	@UniqueValue(domainClass = Categoria.class, fieldName = "nome")
	private String nome;

	private String categoriaMae;

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

	public Categoria converter(int idCategoriaMae) {
		return new Categoria(this.nome, idCategoriaMae);
	}

}
