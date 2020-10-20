package br.com.itau.casadocodigo.ecommerceAPI.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.itau.casadocodigo.ecommerceAPI.model.Caracteristica;
import br.com.itau.casadocodigo.ecommerceAPI.model.Categoria;
import br.com.itau.casadocodigo.ecommerceAPI.model.Imagem;

@Repository
public interface ImagemRepository extends JpaRepository<Imagem, Integer> {

}
