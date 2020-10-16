package br.com.itau.casadocodigo.ecommerceAPI.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.itau.casadocodigo.ecommerceAPI.model.Caracteristica;
import br.com.itau.casadocodigo.ecommerceAPI.model.Categoria;

@Repository
public interface CaracteristicaRepository extends JpaRepository<Caracteristica, Integer> {

}
