package br.com.zup.luanasavian.mercadolivre.compartilhada;

import br.com.zup.luanasavian.mercadolivre.model.Compra;

public interface EventoCompraFalha {

    void processa(Compra compra);
}
