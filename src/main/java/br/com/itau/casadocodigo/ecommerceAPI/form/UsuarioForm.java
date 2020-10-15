package br.com.itau.casadocodigo.ecommerceAPI.form;

import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.itau.casadocodigo.ecommerceAPI.config.validacao.UniqueValue;
import br.com.itau.casadocodigo.ecommerceAPI.model.Usuario;

public class UsuarioForm {

	@JsonIgnore
	@PastOrPresent
	private LocalDateTime instanteCadastro = LocalDateTime.now();
	@NotBlank
	@Email(message = "O email deve estar em um formato válido!")
	@UniqueValue(domainClass = Usuario.class, fieldName = "login")
	private String login;
	@Length(min = 6)
	private String senha;

	public Usuario converter(String senhaEncoded) {

		return new Usuario(this.instanteCadastro, this.login, senhaEncoded);
	}

	public LocalDateTime getInstanteCadastro() {
		return instanteCadastro;
	}

	public void setInstanteCadastro(LocalDateTime instanteCadastro) {
		this.instanteCadastro = instanteCadastro;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
