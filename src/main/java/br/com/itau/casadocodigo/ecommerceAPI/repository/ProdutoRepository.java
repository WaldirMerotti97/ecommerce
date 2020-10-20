package br.com.itau.casadocodigo.ecommerceAPI.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.itau.casadocodigo.ecommerceAPI.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

	Optional<Produto> findByNome(String nome);

}
