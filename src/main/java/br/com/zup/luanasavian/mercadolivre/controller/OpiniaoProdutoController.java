package br.com.zup.luanasavian.mercadolivre.controller;

import br.com.zup.luanasavian.mercadolivre.model.OpiniaoProduto;
import br.com.zup.luanasavian.mercadolivre.model.Produto;
import br.com.zup.luanasavian.mercadolivre.model.Usuario;
import br.com.zup.luanasavian.mercadolivre.repository.OpiniaoProdutoRepository;
import br.com.zup.luanasavian.mercadolivre.repository.ProdutoRepository;
import br.com.zup.luanasavian.mercadolivre.repository.UsuarioRepository;
import br.com.zup.luanasavian.mercadolivre.request.OpiniaoProdutoFormRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/produtos")
public class OpiniaoProdutoController {

        @Autowired
        private ProdutoRepository produtoRepository;
        @Autowired
        private UsuarioRepository usuarioRepository;
        @Autowired
        private OpiniaoProdutoRepository opiniaoProdutoRepository;

        @PostMapping("/{id}/opinioes")
        @Transactional
        public void post (@PathVariable("id") Long id, @RequestBody @Valid OpiniaoProdutoFormRequest form, UriComponentsBuilder uriBuilder) {
            Usuario consumidor = usuarioRepository.findByEmail("harry@email.com").get();
            Produto produto = produtoRepository.findById(id).get();

            OpiniaoProduto opiniao = form.toModel(produto, consumidor);
            opiniaoProdutoRepository.save(opiniao);

            URI uri = uriBuilder.path("/produtos/{id}/opinioes").buildAndExpand(produto.getId()).toUri();
        }
}
