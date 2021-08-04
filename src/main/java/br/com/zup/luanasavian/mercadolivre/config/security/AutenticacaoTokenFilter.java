package br.com.zup.luanasavian.mercadolivre.config.security;

import br.com.zup.luanasavian.mercadolivre.model.Usuario;
import br.com.zup.luanasavian.mercadolivre.repository.UsuarioRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AutenticacaoTokenFilter extends OncePerRequestFilter {


    private TokenServiceClass tokenServiceClass;
    private UsuarioRepository usuarioRep;

    public AutenticacaoTokenFilter(TokenServiceClass tokenServiceClass, UsuarioRepository usuarioRep) {
        this.tokenServiceClass = tokenServiceClass;
        this.usuarioRep = usuarioRep;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = recuperarToken(request);

        boolean valido = tokenServiceClass.isTokenValid(token);
        if(valido) {
            autenticarCliente(token);
        }
        filterChain.doFilter(request, response);
    }

    private void autenticarCliente(String token) {
        Long idUsuario = tokenServiceClass.getIdUser(token);
        Usuario usuario = usuarioRep.findById(idUsuario).get();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String recuperarToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }	else {
            return token.substring(7, token.length());
        }
    }


}