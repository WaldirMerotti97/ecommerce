package br.com.itau.casadocodigo.ecommerceAPI.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

import br.com.itau.casadocodigo.ecommerceAPI.form.CaracteristicaForm;

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
	@OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
	private Set<Caracteristica> caracteristicas = new HashSet<>();
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "categoria_id", referencedColumnName = "id")
	private Categoria categoria;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "usuario_id", referencedColumnName = "id")
	private Usuario usuario;
	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
	private Set<Imagem> imagens = new HashSet<>();

	public Produto(String nome, BigDecimal valor, int quantidadeDisponivel, String descricao,
			List<CaracteristicaForm> listaCaracteristicas, Categoria categoria, Usuario usuario) {
		this.nome = nome;
		this.valor = valor;
		this.quantidadeDisponivel = quantidadeDisponivel;
		this.descricao = descricao;
		this.categoria = categoria;
		this.usuario = usuario;
		Set<Caracteristica> caracteristicas = listaCaracteristicas.stream().map(car -> car.converter(this))
				.collect(Collectors.toSet());
		this.caracteristicas.addAll(caracteristicas);
	}

	public Produto() {

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

	public Set<Caracteristica> getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(Set<Caracteristica> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Set<Imagem> getImagens() {
		return imagens;
	}

	public void setImagens(Set<Imagem> imagens) {
		this.imagens = imagens;
	}

	public void associaImagens(Set<String> links) {

		Set<Imagem> imagens = links.stream().map(link -> new Imagem(this, link)).collect(Collectors.toSet());
		this.imagens.addAll(imagens);
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", valor=" + valor + ", quantidadeDisponivel="
				+ quantidadeDisponivel + ", descricao=" + descricao + ", instanteCadastro=" + instanteCadastro
				+ ", caracteristicas=" + caracteristicas + ", categoria=" + categoria + ", usuario=" + usuario
				+ ", imagens=" + imagens + "]";
	}

	public boolean pertenceAoUsuario(Usuario usuario) {

		return this.usuario.getId() == usuario.getId();
	}

}
