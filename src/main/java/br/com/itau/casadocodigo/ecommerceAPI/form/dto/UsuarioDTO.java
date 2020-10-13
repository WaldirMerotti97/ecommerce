package br.com.itau.casadocodigo.ecommerceAPI.form.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UsuarioDTO {

	private int id;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime instanteCadastro;
	private String login;

	@Deprecated
	public UsuarioDTO() {

	}

	public UsuarioDTO(int id, LocalDateTime instanteCadastro, String login) {
		this.id = id;
		this.instanteCadastro = instanteCadastro;
		this.login = login;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public LocalDateTime getInstanteCadastro() {
		return instanteCadastro;
	}

	public void setInstanteCadastro(LocalDateTime instanteCadastro) {
		this.instanteCadastro = instanteCadastro;
	}

}
