package br.com.itau.casadocodigo.ecommerceAPI.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.itau.casadocodigo.ecommerceAPI.model.Usuario;
import br.com.itau.casadocodigo.ecommerceAPI.repository.UsuarioRepository;

@Service
public class AutenticacaoService implements UserDetailsService {

	// implementamos essa interface pois a mesma eh necessaria para informar ao
	// spring que ela contem a logica de autenticacao do usuario

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Usuario> usuario = usuarioRepository.findByLogin(username);
		if (usuario.isPresent()) {
			return (UserDetails) usuario.get();
		}

		throw new UsernameNotFoundException("Dados invalidos");

	}

}
