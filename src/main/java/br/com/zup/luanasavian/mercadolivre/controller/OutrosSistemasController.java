package br.com.zup.luanasavian.mercadolivre.controller;

import br.com.zup.luanasavian.mercadolivre.service.NotaFiscal;
import br.com.zup.luanasavian.mercadolivre.service.Ranking;
import br.com.zup.luanasavian.mercadolivre.request.NovaCompraNFRequest;
import br.com.zup.luanasavian.mercadolivre.request.RankingNovaCompraRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class OutrosSistemasController {

    @Autowired
    private NotaFiscal notaFiscal;
    private Ranking ranking;

    @PostMapping(value = "/notas-fiscais")
    public void criaNota(@Valid @RequestBody NovaCompraNFRequest form) throws InterruptedException {
        System.out.println("criando nota para "+form);
        Thread.sleep(150);
    }

    @PostMapping(value = "/ranking")
    public void ranking(@Valid @RequestBody RankingNovaCompraRequest form) throws InterruptedException {
        System.out.println("criando ranking"+form);
        Thread.sleep(150);
    }
}
