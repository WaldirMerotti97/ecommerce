package br.com.itau.casadocodigo.ecommerceAPI.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.itau.casadocodigo.ecommerceAPI.config.validacao.CategoriaMaeValidator;
import br.com.itau.casadocodigo.ecommerceAPI.form.CategoriaForm;
import br.com.itau.casadocodigo.ecommerceAPI.form.dto.CategoriaDTO;
import br.com.itau.casadocodigo.ecommerceAPI.model.Categoria;
import br.com.itau.casadocodigo.ecommerceAPI.repository.CategoriaRepository;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

	// 1
	private CategoriaRepository categoriaRepository;
	// 1
	private CategoriaMaeValidator categoriaMaeValidator;

	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(this.categoriaMaeValidator);
	}

	public CategoriaController(CategoriaRepository categoriaRepository, CategoriaMaeValidator categoriaMaeValidator) {
		this.categoriaRepository = categoriaRepository;
		this.categoriaMaeValidator = categoriaMaeValidator;
	}

	@PostMapping(value = "/cadastrarCategoria")
	// 1
	public ResponseEntity<CategoriaDTO> cadastrarUsuario(
			@RequestBody(required = true) @Valid CategoriaForm categoriaForm,
			UriComponentsBuilder uriComponentsBuilder) {

		// 1
		Optional<Categoria> categoriaMae = categoriaRepository.findByNome(categoriaForm.getCategoriaMae());
		// 1 //1
		int idCategoriaMae = categoriaMae.isPresent() ? categoriaMae.get().getId() : 0;

		Categoria categoria = categoriaForm.converter(idCategoriaMae);

		categoriaRepository.save(categoria);

		URI uri = uriComponentsBuilder.path("/categorias/{id}").buildAndExpand(categoria.getId()).toUri();

		return ResponseEntity.created(uri)
				.body(new CategoriaDTO(categoria.getId(), categoria.getNome(), categoriaForm.getCategoriaMae()));

	}

}
