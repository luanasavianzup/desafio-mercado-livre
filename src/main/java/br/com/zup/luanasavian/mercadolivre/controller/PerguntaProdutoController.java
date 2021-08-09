package br.com.zup.luanasavian.mercadolivre.controller;


import br.com.zup.luanasavian.mercadolivre.compartilhada.Emails;
import br.com.zup.luanasavian.mercadolivre.model.PerguntaProduto;
import br.com.zup.luanasavian.mercadolivre.model.Produto;
import br.com.zup.luanasavian.mercadolivre.model.Usuario;
import br.com.zup.luanasavian.mercadolivre.repository.PerguntaProdutoRepository;
import br.com.zup.luanasavian.mercadolivre.repository.ProdutoRepository;
import br.com.zup.luanasavian.mercadolivre.repository.UsuarioRepository;
import br.com.zup.luanasavian.mercadolivre.request.PerguntaProdutoFormRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/produtos")
public class PerguntaProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private PerguntaProdutoRepository perguntaProdutoRepository;
    @Autowired
    private Emails emails;

    @PostMapping("/{id}/perguntas")
    @Transactional
    public void post (@RequestBody @Valid PerguntaProdutoFormRequest form, @PathVariable("id") Long id, UriComponentsBuilder uriBuilder, @AuthenticationPrincipal Usuario consumidor) {
        Produto produto = produtoRepository.findById(id).get();

        PerguntaProduto pergunta = form.toModel(produto, consumidor);
        perguntaProdutoRepository.save(pergunta);

        emails.novaPergunta(pergunta);

        URI uri = uriBuilder.path("/produtos/{id}/perguntas").buildAndExpand(produto.getId()).toUri();
    }
}
