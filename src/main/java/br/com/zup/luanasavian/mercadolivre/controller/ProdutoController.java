package br.com.zup.luanasavian.mercadolivre.controller;

import br.com.zup.luanasavian.mercadolivre.compartilhada.Uploader;
import br.com.zup.luanasavian.mercadolivre.model.Produto;
import br.com.zup.luanasavian.mercadolivre.model.Usuario;
import br.com.zup.luanasavian.mercadolivre.repository.ProdutoRepository;
import br.com.zup.luanasavian.mercadolivre.repository.UsuarioRepository;
import br.com.zup.luanasavian.mercadolivre.request.ImagemProdutoFormRequest;
import br.com.zup.luanasavian.mercadolivre.request.ProdutoFormRequest;
import br.com.zup.luanasavian.mercadolivre.validation.ProibeCaracteristicasComNomeIgualValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Set;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private Uploader fakeUploader;

    @PersistenceContext
    private EntityManager manager;

    @InitBinder(value = "ProdutoFormRequest")
    public void init(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(new ProibeCaracteristicasComNomeIgualValidator());
    }

    @PostMapping
    @Transactional
    public void post(@RequestBody @Valid ProdutoFormRequest form, UriComponentsBuilder uriBuilder,  @AuthenticationPrincipal Usuario dono) {
        //Usuario dono = usuarioRepository.findByEmail("harry@email.com").get();
        Produto produto = form.toModel(manager, dono);
        produtoRepository.save(produto);

        URI uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();
    }

    @PostMapping("{id}/imagens")
    @Transactional
    public void adicionaImagensProduto(@PathVariable("id") Long id, @Valid ImagemProdutoFormRequest form, UriComponentsBuilder uriBuilder,  @AuthenticationPrincipal Usuario dono) {
        Produto produto = produtoRepository.findById(id).get();

        if(!produto.pertenceAoUsuario(dono)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        Set<String> links = fakeUploader.envia(form.getImagens());
        produto.associaImagens(links);

        produtoRepository.save(produto);

        URI uri = uriBuilder.path("/produtos/{id}/imagens").buildAndExpand(produto.getId()).toUri();
    }
}
