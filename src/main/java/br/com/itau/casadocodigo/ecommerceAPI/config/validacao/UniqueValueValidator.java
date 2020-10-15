package br.com.itau.casadocodigo.ecommerceAPI.config.validacao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, String> {

	private String nomeDominio;
	private Class<?> klass;
	@PersistenceContext
	private EntityManager manager;

	@Override
	public void initialize(UniqueValue uniqueValue) {
		this.nomeDominio = uniqueValue.fieldName();
		this.klass = uniqueValue.domainClass();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		Query query = manager.createQuery("select 1 from " + klass.getName() + " where " + nomeDominio + "=:value");
		query.setParameter("value", value);
		List<?> list = query.getResultList();
//		Assert.state(list.size() <= 1,
//				"Foi encontrado mais de um " + klass + " com o atributo " + nomeDominio + " = " + value);

		return list.isEmpty();
	}

}
