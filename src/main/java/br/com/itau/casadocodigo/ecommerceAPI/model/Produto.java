package br.com.itau.casadocodigo.ecommerceAPI.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "produto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nome;
	private BigDecimal valor;
	private int quantidadeDisponivel;
	private String descricao;
	private LocalDateTime instanteCadastro = LocalDateTime.now();
	@OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
	private List<Caracteristica> caracteristicas;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "categoria_id", referencedColumnName = "id")
	private Categoria categoria;

	public Produto(String nome, BigDecimal valor, int quantidadeDisponivel, String descricao, Categoria categoria,
			List<Caracteristica> listaCaracteristicas) {
		this.nome = nome;
		this.valor = valor;
		this.quantidadeDisponivel = quantidadeDisponivel;
		this.descricao = descricao;
		this.categoria = categoria;
		this.caracteristicas = listaCaracteristicas;
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

	public LocalDateTime getInstanteCadastro() {
		return instanteCadastro;
	}

	public void setInstanteCadastro(LocalDateTime instanteCadastro) {
		this.instanteCadastro = instanteCadastro;
	}

	public List<Caracteristica> getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(List<Caracteristica> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
