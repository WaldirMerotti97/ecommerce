package br.com.itau.casadocodigo.ecommerceAPI.form;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class NovasImagensForm {

	@Size(min = 1)
	@NotNull
	List<MultipartFile> imagens = new ArrayList<>();

	public NovasImagensForm(@Size(min = 1) List<MultipartFile> imagens) {
		this.imagens = imagens;
	}

	public List<MultipartFile> getImagens() {
		return imagens;
	}

	public void setImagens(List<MultipartFile> imagens) {
		this.imagens = imagens;
	}

}
