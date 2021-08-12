package br.com.zup.luanasavian.mercadolivre.request;

import br.com.zup.luanasavian.mercadolivre.compartilhada.RetornoGatewayPagamento;
import br.com.zup.luanasavian.mercadolivre.compartilhada.StatusRetornoPagseguro;
import br.com.zup.luanasavian.mercadolivre.model.Compra;
import br.com.zup.luanasavian.mercadolivre.model.Pagseguro;
import br.com.zup.luanasavian.mercadolivre.model.Transacao;

import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PagseguroFormRequest implements RetornoGatewayPagamento {

    @NotBlank
    private String idTransacao;
    @NotNull
    @Enumerated
    private StatusRetornoPagseguro status;

    public PagseguroFormRequest(@NotBlank String idTransacao, @NotNull StatusRetornoPagseguro status) {
        this.idTransacao = idTransacao;
        this.status = status;
    }

    public Pagseguro toModel() {

        return new Pagseguro(this.idTransacao, this.status);
    }

    public Transacao toTransacao(Compra compra) {
        return new Transacao(status.normaliza(), idTransacao, compra);
    }
}
