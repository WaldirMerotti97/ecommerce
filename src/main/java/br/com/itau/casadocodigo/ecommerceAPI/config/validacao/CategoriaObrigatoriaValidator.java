package br.com.itau.casadocodigo.ecommerceAPI.config.validacao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CategoriaObrigatoriaValidator implements ConstraintValidator<CategoriaObrigatoria, Integer> {

	private String nomeDominio;
	private Class<?> klass;
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void initialize(CategoriaObrigatoria categoriaObrigatoria) {
		this.klass = categoriaObrigatoria.domainClass();
		this.nomeDominio = categoriaObrigatoria.fieldName();
	}

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {

		Query query = entityManager
				.createQuery("select 1 from " + klass.getName() + " where " + nomeDominio + "=:value");
		query.setParameter("value", value);
		List<?> resultList = query.getResultList();

		if (!resultList.isEmpty())
			return true;

		return false;
	}

}
