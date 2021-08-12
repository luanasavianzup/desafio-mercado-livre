package br.com.zup.luanasavian.mercadolivre.request;

import br.com.zup.luanasavian.mercadolivre.compartilhada.RetornoGatewayPagamento;
import br.com.zup.luanasavian.mercadolivre.compartilhada.StatusTransacao;
import br.com.zup.luanasavian.mercadolivre.model.Compra;
import br.com.zup.luanasavian.mercadolivre.model.Pagseguro;
import br.com.zup.luanasavian.mercadolivre.model.Paypal;
import br.com.zup.luanasavian.mercadolivre.model.Transacao;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PaypalFormRequest implements RetornoGatewayPagamento {

    @Min(0)
    @Max(1)
    private int status;
    @NotBlank
    private String idTransacao;

    public PaypalFormRequest(@Min(0) @Max(1) int status, @NotBlank String idTransacao) {
        this.status = status;
        this.idTransacao = idTransacao;
    }

    public Paypal toModel() {

        return new Paypal(this.idTransacao);
    }

    public Transacao toTransacao(Compra compra) {
        @NotNull
        StatusTransacao statusCalculado = this.status == 0? StatusTransacao.erro:StatusTransacao.sucesso;
        return new Transacao(statusCalculado, idTransacao, compra);
    }
}
