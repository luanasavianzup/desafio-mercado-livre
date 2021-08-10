package br.com.zup.luanasavian.mercadolivre.model;

import br.com.zup.luanasavian.mercadolivre.compartilhada.GatewayPagamento;
import br.com.zup.luanasavian.mercadolivre.request.CompraFormRequest;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "tb_compra")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Valid
    @ManyToOne
    private Produto produto;
    @Positive
    private int quantidade;
    @NotNull
    @Valid
    @ManyToOne
    private Usuario comprador;
    @Enumerated
    @NotNull
    private GatewayPagamento gateway;

    @Deprecated
    public Compra() {

    }

    public Compra(@NotNull @Valid Produto produto, @Positive int quantidade, @NotNull @Valid Usuario comprador, GatewayPagamento gateway) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.comprador = comprador;
        this.gateway = gateway;
    }

    public Long getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Usuario getComprador() {
        return comprador;
    }

    public GatewayPagamento getGateway() {
        return gateway;
    }

}
