package br.com.zup.luanasavian.mercadolivre.controller;

import br.com.zup.luanasavian.mercadolivre.model.Produto;
import br.com.zup.luanasavian.mercadolivre.model.Usuario;
import br.com.zup.luanasavian.mercadolivre.repository.ProdutoRepository;
import br.com.zup.luanasavian.mercadolivre.repository.UsuarioRepository;
import br.com.zup.luanasavian.mercadolivre.request.ProdutoFormRequest;
import br.com.zup.luanasavian.mercadolivre.validation.ProibeCaracteristicasComNomeIgualValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PersistenceContext
    private EntityManager manager;

    @InitBinder
    public void init(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(new ProibeCaracteristicasComNomeIgualValidator());
    }

    @PostMapping
    @Transactional
    public void post(@RequestBody @Valid ProdutoFormRequest form, UriComponentsBuilder uriBuilder) {
        Usuario dono = usuarioRepository.findByEmail("harry@email.com").get();
        Produto produto = form.toModel(manager, dono);
        produtoRepository.save(produto);

        URI uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();
    }

}
