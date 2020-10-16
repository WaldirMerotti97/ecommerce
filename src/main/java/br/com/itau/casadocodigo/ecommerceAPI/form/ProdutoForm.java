package br.com.itau.casadocodigo.ecommerceAPI.form;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import br.com.itau.casadocodigo.ecommerceAPI.config.validacao.CategoriaObrigatoria;
import br.com.itau.casadocodigo.ecommerceAPI.model.Caracteristica;
import br.com.itau.casadocodigo.ecommerceAPI.model.Categoria;
import br.com.itau.casadocodigo.ecommerceAPI.model.Produto;

public class ProdutoForm {

	@NotBlank
	private String nome;
	@NotNull
	@DecimalMin(value = "0.0")
	private BigDecimal valor;
	@Min(0)
	private int quantidadeDisponivel;
	@NotBlank
	@CategoriaObrigatoria(domainClass = Categoria.class, fieldName = "nome")
	private String categoria;
	@NotBlank
	@Length(max = 1000)
	private String descricao;
	@Size(min = 3)
	private List<Caracteristica> caracteristicas;

	public Produto converter(Categoria categoria) {

		return new Produto(this.nome, this.valor, this.quantidadeDisponivel, this.descricao, categoria);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public int getQuantidadeDisponivel() {
		return quantidadeDisponivel;
	}

	public void setQuantidadeDisponivel(int quantidadeDisponivel) {
		this.quantidadeDisponivel = quantidadeDisponivel;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Caracteristica> getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(List<Caracteristica> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

}
