package br.com.zup.luanasavian.mercadolivre.model;

import br.com.zup.luanasavian.mercadolivre.compartilhada.StatusRetornoPagseguro;

import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Paypal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String idTransacao;

    public Paypal(@NotBlank String idTransacao) {
        this.idTransacao = idTransacao;
    }

    public Long getId() {
        return id;
    }

}
