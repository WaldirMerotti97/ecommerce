package br.com.itau.casadocodigo.ecommerceAPI.form.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import br.com.itau.casadocodigo.ecommerceAPI.model.Caracteristica;

public class ProdutoDTO {

	private int id;
	private String nome;
	private BigDecimal valor;
	private int quantidadeDisponivel;
	private LocalDateTime instanteCadastro;
	private String descricao;
	private List<Caracteristica> caracteristicas;

	public ProdutoDTO(int id, String nome, BigDecimal valor, int quantidadeDisponivel, LocalDateTime instanteCadastro,
			String descricao, List<Caracteristica> caracteristicas) {
		this.id = id;
		this.nome = nome;
		this.valor = valor;
		this.quantidadeDisponivel = quantidadeDisponivel;
		this.instanteCadastro = instanteCadastro;
		this.descricao = descricao;
		this.caracteristicas = caracteristicas;

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

	public LocalDateTime getInstanteCadastro() {
		return instanteCadastro;
	}

	public void setInstanteCadastro(LocalDateTime instanteCadastro) {
		this.instanteCadastro = instanteCadastro;
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

}
