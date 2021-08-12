package br.com.zup.luanasavian.mercadolivre.controller;

import br.com.zup.luanasavian.mercadolivre.model.Compra;
import br.com.zup.luanasavian.mercadolivre.model.Pagseguro;
import br.com.zup.luanasavian.mercadolivre.model.Paypal;
import br.com.zup.luanasavian.mercadolivre.repository.CompraRepository;
import br.com.zup.luanasavian.mercadolivre.request.PagseguroFormRequest;
import br.com.zup.luanasavian.mercadolivre.request.PaypalFormRequest;
import br.com.zup.luanasavian.mercadolivre.service.EventosNovaCompra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/compras")
public class CompraController2 {

    @Autowired
    private CompraRepository compraRepository;
    @Autowired
    private EventosNovaCompra eventosNovaCompra;

    @PostMapping("/retorno-pagseguro/{id}")
    @Transactional
    public ResponseEntity<?> pagseguro(@PathVariable("id") Long idCompra, @Valid PagseguroFormRequest form, UriComponentsBuilder uriBuilder) {
        Compra compra = compraRepository.findById(idCompra).get();

        if(compra.concluida()) {
            return ResponseEntity.badRequest().build();
        }

        Pagseguro pagseguro = form.toModel();
        compra.adicionaTransacao(form);

        compraRepository.save(compra);

        URI uri = uriBuilder.path("/compras/retorno-pagseguro/{id}").buildAndExpand(compra.getId()).toUri();

        return ResponseEntity.ok().build();
    }

    @PostMapping("/retorno-paypal/{id}")
    @Transactional
    public ResponseEntity<?> paypal(@PathVariable("id") Long idCompra, @Valid PaypalFormRequest form, UriComponentsBuilder uriBuilder) {
        Compra compra = compraRepository.findById(idCompra).get();

        if(compra.concluida()) {
            return ResponseEntity.badRequest().build();
        }

        eventosNovaCompra.processa(compra);

        Paypal paypal = form.toModel();
        compra.adicionaTransacao(form);

        compraRepository.save(compra);

        URI uri = uriBuilder.path("/compras/retorno-paypal/{id}").buildAndExpand(compra.getId()).toUri();

        return ResponseEntity.ok().build();
    }
}
