package br.com.itau.casadocodigo.ecommerceAPI.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import br.com.itau.casadocodigo.ecommerceAPI.config.validacao.CategoriaObrigatoria;
import br.com.itau.casadocodigo.ecommerceAPI.config.validacao.UniqueValue;
import br.com.itau.casadocodigo.ecommerceAPI.model.Caracteristica;
import br.com.itau.casadocodigo.ecommerceAPI.model.Categoria;
import br.com.itau.casadocodigo.ecommerceAPI.model.Produto;
import br.com.itau.casadocodigo.ecommerceAPI.model.Usuario;

public class ProdutoForm {

	@NotBlank
	@UniqueValue(domainClass = Produto.class, fieldName = "nome")
	private String nome;
	@NotNull
	@Positive
	private BigDecimal valor;
	@Positive
	private int quantidadeDisponivel;
	@NotNull
	@CategoriaObrigatoria(domainClass = Categoria.class, fieldName = "id")
	private int categoria;
	@NotBlank
	@Length(max = 1000)
	private String descricao;
	@Size(min = 3)
	@Valid
	private List<CaracteristicaForm> caracteristicas = new ArrayList<>();

	public ProdutoForm(@NotBlank String nome, @NotNull @Positive BigDecimal valor, @Positive int quantidadeDisponivel,
			@NotNull int categoria, @NotBlank @Length(max = 1000) String descricao,
			@Size(min = 3) List<CaracteristicaForm> caracteristicas) {
		this.nome = nome;
		this.valor = valor;
		this.quantidadeDisponivel = quantidadeDisponivel;
		this.categoria = categoria;
		this.descricao = descricao;
		this.caracteristicas = caracteristicas;
	}

	public Produto converter(Usuario usuario, EntityManager entityManager) {
		Categoria categoria = entityManager.find(Categoria.class, this.categoria);
		return new Produto(this.nome, this.valor, this.quantidadeDisponivel, this.descricao, this.caracteristicas,
				categoria, usuario);
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

	public List<CaracteristicaForm> getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(List<CaracteristicaForm> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	public int getCategoria() {
		return categoria;
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "ProdutoForm [nome=" + nome + ", valor=" + valor + ", quantidadeDisponivel=" + quantidadeDisponivel
				+ ", categoria=" + categoria + ", descricao=" + descricao + ", caracteristicas=" + caracteristicas
				+ "]";
	}

	public boolean temCaracteristicasIguais() {
		HashSet<String> nomesIguais = new HashSet<>();
		for (CaracteristicaForm car : caracteristicas) {
			if (!nomesIguais.add(car.getNome())) {
				return true;
			}
		}
		return false;
	}

}
