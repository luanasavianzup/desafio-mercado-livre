package br.com.zup.luanasavian.mercadolivre.controller;

import br.com.zup.luanasavian.mercadolivre.compartilhada.GatewayPagamento;
import br.com.zup.luanasavian.mercadolivre.model.Compra;
import br.com.zup.luanasavian.mercadolivre.model.Produto;
import br.com.zup.luanasavian.mercadolivre.model.Usuario;
import br.com.zup.luanasavian.mercadolivre.repository.CompraRepository;
import br.com.zup.luanasavian.mercadolivre.repository.ProdutoRepository;
import br.com.zup.luanasavian.mercadolivre.repository.UsuarioRepository;
import br.com.zup.luanasavian.mercadolivre.request.CompraFormRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.validation.BindException;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/compras")
public class CompraController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CompraRepository compraRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> post(@RequestBody @Valid CompraFormRequest form, UriComponentsBuilder uriBuilder, @AuthenticationPrincipal Usuario comprador) throws BindException {
        Produto produto = produtoRepository.findById(form.getIdProduto()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));;

        int quantidade = form.getQuantidade();
        Usuario usuario = usuarioRepository.findByEmail(comprador.getUsername()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
        boolean abateu = produto.abateEstoque(quantidade);

        if(abateu) {
            GatewayPagamento gateway = form.getGateway();
            Compra novaCompra = new Compra(produto, quantidade, usuario, gateway);
            compraRepository.save(novaCompra);

            if(gateway.equals(GatewayPagamento.PAGSEGURO)) {
                URI uri = uriBuilder.path("/retorno-pagseguro/{id}").buildAndExpand(novaCompra.getId()).toUri();
                return ResponseEntity.status(HttpStatus.FOUND).location(uri).build();

            } else {
                URI uri = uriBuilder.path("/retorno-paypal/{id}").buildAndExpand(novaCompra.getId()).toUri();
               return ResponseEntity.status(HttpStatus.FOUND).location(uri).build();
            }
        }

        BindException problemaComEstoque = new BindException(form, "CompraFormRequest");
        problemaComEstoque.reject(null, "A quantidade do produto é menor que o estoque.");
        throw problemaComEstoque;

    }
}
