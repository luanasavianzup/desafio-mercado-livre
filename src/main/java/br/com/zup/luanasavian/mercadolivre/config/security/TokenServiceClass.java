package br.com.zup.luanasavian.mercadolivre.config.security;

import br.com.zup.luanasavian.mercadolivre.model.Usuario;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

@Service
public class TokenServiceClass {

    @Value("${mercadolivre.jwt.expiration}")
    private String expiration;

    @Value("${mercadolivre.jwt.secret}")
    private String secret;

    public String gerarToken(Authentication aut) {
        Usuario estaLogado = (Usuario) aut.getPrincipal();
        Date hoje = new Date();
        Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));

        return Jwts.builder()
                .setIssuer("Api Mercado Livre")
                .setSubject(estaLogado.getId().toString())
                .setIssuedAt(dataExpiracao)
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long getIdUser(String token) {
        Claims body = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Long.parseLong(body.getSubject());
    }
}
