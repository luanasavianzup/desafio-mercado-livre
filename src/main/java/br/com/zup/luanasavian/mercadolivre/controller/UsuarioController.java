package br.com.zup.luanasavian.mercadolivre.controller;

import br.com.zup.luanasavian.mercadolivre.model.Usuario;
import br.com.zup.luanasavian.mercadolivre.repository.UsuarioRepository;
import br.com.zup.luanasavian.mercadolivre.request.UsuarioFormRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public void post(@RequestBody @Valid UsuarioFormRequest form, UriComponentsBuilder uriBuilder) {
        Usuario usuario = form.toModel();
        usuarioRepository.save(usuario);

        URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
    }
}
