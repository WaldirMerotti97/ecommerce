package br.com.itau.casadocodigo.ecommerceAPI.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.itau.casadocodigo.ecommerceAPI.form.UsuarioForm;
import br.com.itau.casadocodigo.ecommerceAPI.form.dto.UsuarioDTO;
import br.com.itau.casadocodigo.ecommerceAPI.model.Usuario;
import br.com.itau.casadocodigo.ecommerceAPI.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	// 1
	private UsuarioRepository usuarioRepository;
	// 1
	private PasswordEncoder bCryptPasswordEncoder;

	public UsuarioController(UsuarioRepository usuarioRepository, PasswordEncoder bCryptPasswordEncoder) {
		this.usuarioRepository = usuarioRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@PostMapping(value = "/cadastrarUsuario")
	// 1
	public ResponseEntity<UsuarioDTO> cadastrarUsuario(@RequestBody(required = true) @Valid UsuarioForm usuarioForm) {

		String senhaEncoded = bCryptPasswordEncoder.encode(usuarioForm.getSenha());

		// 1
		Usuario usuario = usuarioForm.converter(senhaEncoded);

		usuarioRepository.save(usuario);

		return ResponseEntity.ok()
				.body(new UsuarioDTO(usuario.getId(), usuario.getInstanteCadastro(), usuario.getLogin()));

	}

}
