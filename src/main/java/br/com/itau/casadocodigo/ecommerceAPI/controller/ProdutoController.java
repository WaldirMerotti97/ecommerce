package br.com.itau.casadocodigo.ecommerceAPI.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.itau.casadocodigo.ecommerceAPI.form.CategoriaForm;
import br.com.itau.casadocodigo.ecommerceAPI.form.ProdutoForm;
import br.com.itau.casadocodigo.ecommerceAPI.form.dto.CategoriaDTO;
import br.com.itau.casadocodigo.ecommerceAPI.form.dto.ProdutoDTO;
import br.com.itau.casadocodigo.ecommerceAPI.model.Categoria;
import br.com.itau.casadocodigo.ecommerceAPI.model.Produto;
import br.com.itau.casadocodigo.ecommerceAPI.repository.CaracteristicaRepository;
import br.com.itau.casadocodigo.ecommerceAPI.repository.CategoriaRepository;
import br.com.itau.casadocodigo.ecommerceAPI.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	// 1
	private CategoriaRepository categoriaRepository;
	// 1
	private ProdutoRepository produtoRepository;
	// 1
	private CaracteristicaRepository caracteristicaRepository;

	public ProdutoController(CategoriaRepository categoriaRepository, ProdutoRepository produtoRepository,
			CaracteristicaRepository caracteristicaRepository) {
		this.categoriaRepository = categoriaRepository;
		this.produtoRepository = produtoRepository;
		this.caracteristicaRepository = caracteristicaRepository;
	}

	@PostMapping(value = "/cadastrarProduto")
	// 1
	public ResponseEntity<ProdutoDTO> cadastrarProduto(@RequestBody(required = true) @Valid ProdutoForm produtoForm,
			UriComponentsBuilder uriComponentsBuilder) {

		// 1
		Optional<Categoria> categoria = categoriaRepository.findByNome(produtoForm.getCategoria());

		// 1
		Produto produto = produtoForm.converter(categoria.get(), produtoForm.getCaracteristicas());

		produtoRepository.save(produto);

		URI uri = uriComponentsBuilder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();

		return ResponseEntity.created(uri)
				.body(new ProdutoDTO(produto.getId(), produto.getNome(), produto.getValor(),
						produto.getQuantidadeDisponivel(), produto.getInstanteCadastro(), produto.getDescricao(),
						produto.getCaracteristicas()));

	}

}
