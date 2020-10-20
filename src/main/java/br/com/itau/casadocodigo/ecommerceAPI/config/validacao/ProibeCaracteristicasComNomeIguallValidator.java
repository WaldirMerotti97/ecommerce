package br.com.itau.casadocodigo.ecommerceAPI.config.validacao;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.itau.casadocodigo.ecommerceAPI.form.ProdutoForm;

public class ProibeCaracteristicasComNomeIguallValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {

		return ProdutoForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		if (errors.hasErrors()) {
			return;
		}

		ProdutoForm produtoForm = (ProdutoForm) target;

		if (produtoForm.temCaracteristicasIguais()) {
			errors.rejectValue("caracteristicas", null, "Há caracteristicas iguais! favor retirar para cadastrá-las!");
		}

	}

}
