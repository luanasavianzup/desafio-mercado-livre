package br.com.zup.luanasavian.mercadolivre.compartilhada;

import br.com.zup.luanasavian.mercadolivre.model.Compra;
import br.com.zup.luanasavian.mercadolivre.model.Transacao;

public interface RetornoGatewayPagamento {

    Transacao toTransacao(Compra compra);
}
