package br.com.itau.casadocodigo.ecommerceAPI.config.validacao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.itau.casadocodigo.ecommerceAPI.form.CategoriaForm;
import br.com.itau.casadocodigo.ecommerceAPI.model.Categoria;

@Component
public class CategoriaMaeValidator implements Validator {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public boolean supports(Class<?> clazz) {

		return CategoriaForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		CategoriaForm categoriaForm = (CategoriaForm) target;

		if (categoriaForm.getCategoriaMae().length() > 0) {
			Query query = manager.createQuery("select 1 from Categoria where nome =:value");
			query.setParameter("value", categoriaForm.getCategoriaMae());
			@SuppressWarnings("unchecked")
			List<Categoria> list = query.getResultList();
			Assert.state(list.size() == 1,
					"A categoria mãe informada não existe. Se a mesma precisa ser mãe, primeiro a crie e depois crie uma filha para a mesma");
		}

	}

}
