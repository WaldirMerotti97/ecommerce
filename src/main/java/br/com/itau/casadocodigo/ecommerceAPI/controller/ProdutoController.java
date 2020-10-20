package br.com.itau.casadocodigo.ecommerceAPI.controller;

import java.net.URI;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.itau.casadocodigo.ecommerceAPI.config.validacao.ProibeCaracteristicasComNomeIguallValidator;
import br.com.itau.casadocodigo.ecommerceAPI.form.NovasImagensForm;
import br.com.itau.casadocodigo.ecommerceAPI.form.ProdutoForm;
import br.com.itau.casadocodigo.ecommerceAPI.form.dto.ProdutoDTO;
import br.com.itau.casadocodigo.ecommerceAPI.model.Produto;
import br.com.itau.casadocodigo.ecommerceAPI.model.Usuario;
import br.com.itau.casadocodigo.ecommerceAPI.repository.ProdutoRepository;
import br.com.itau.casadocodigo.ecommerceAPI.utils.UploaderFake;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	private UploaderFake uploaderFake;
	@PersistenceContext
	private EntityManager entityManager;

	@InitBinder(value = "produtoForm")
	private void init(WebDataBinder binder) {
		binder.addValidators(new ProibeCaracteristicasComNomeIguallValidator());
	}

	public ProdutoController(UploaderFake uploaderFake) {
		this.uploaderFake = uploaderFake;
	}

	@PostMapping(value = "/cadastrarProduto")
	@Transactional
	// 1
	public ResponseEntity<ProdutoDTO> cadastrarProduto(@AuthenticationPrincipal Usuario usuario,
			@RequestBody(required = true) @Valid ProdutoForm produtoForm, UriComponentsBuilder uriComponentsBuilder) {

		// 1
		Produto produto = produtoForm.converter(usuario, entityManager);

		entityManager.persist(produto);

		URI uri = uriComponentsBuilder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();

		return ResponseEntity.created(uri)
				.body(new ProdutoDTO(produto.getId(), produto.getNome(), produto.getValor(),
						produto.getQuantidadeDisponivel(), produto.getInstanteCadastro(), produto.getDescricao(),
						produto.getCaracteristicas()));

	}

	@PostMapping(value = "/{id}/imagens")
	@Transactional
	public String adicionaImagens(@AuthenticationPrincipal Usuario usuario, @PathVariable int id,
			@Valid NovasImagensForm novasImagensForm) {

		Set<String> links = uploaderFake.envia(novasImagensForm.getImagens());
		Produto produto = entityManager.find(Produto.class, id);

		if (!produto.pertenceAoUsuario(usuario)) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}

		produto.associaImagens(links);
		entityManager.merge(produto);

		return produto.toString();
	}

}
