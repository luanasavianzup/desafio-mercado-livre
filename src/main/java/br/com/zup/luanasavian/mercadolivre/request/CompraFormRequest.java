package br.com.zup.luanasavian.mercadolivre.request;

import br.com.zup.luanasavian.mercadolivre.compartilhada.GatewayPagamento;
import br.com.zup.luanasavian.mercadolivre.model.Compra;
import br.com.zup.luanasavian.mercadolivre.model.Produto;
import br.com.zup.luanasavian.mercadolivre.model.Usuario;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CompraFormRequest {

    @Positive
    private int quantidade;
    @NotNull
    private Long idProduto;
    @NotNull
    private GatewayPagamento gateway;

    public CompraFormRequest(@Positive int quantidade, @NotNull Long idProduto, GatewayPagamento gateway) {
        this.quantidade = quantidade;
        this.idProduto = idProduto;
        this.gateway = gateway;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public GatewayPagamento getGateway() {
        return gateway;
    }

    public Compra toModel(@NotNull @Valid Produto produto, @NotNull @Valid Usuario comprador) {
        return new Compra(produto, quantidade, comprador, gateway);

    }
}
