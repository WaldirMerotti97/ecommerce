package br.com.itau.casadocodigo.ecommerceAPI.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.itau.casadocodigo.ecommerceAPI.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value(value = "${ecommerce.jwt.expiration}")
	private String expiration;

	@Value(value = "${ecommerce.jwt.secret}")
	private String secret;

	public String gerarToken(Authentication authentication) {

		Usuario logado = (Usuario) authentication.getPrincipal();
		Date hoje = new Date();
		Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));

		return Jwts.builder().setIssuer("API de Ecommerce Mercado Livre")
				.setSubject(String.valueOf(logado.getId()).toString()).setIssuedAt(hoje).setExpiration(dataExpiracao)
				.signWith(SignatureAlgorithm.HS256, secret).compact();
	}

	public boolean isTokenValido(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public int getIdUsuario(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Integer.parseInt(claims.getSubject());

	}

}
