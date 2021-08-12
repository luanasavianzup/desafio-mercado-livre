package br.com.zup.luanasavian.mercadolivre.model;

import br.com.zup.luanasavian.mercadolivre.compartilhada.GatewayPagamento;
import br.com.zup.luanasavian.mercadolivre.compartilhada.RetornoGatewayPagamento;
import br.com.zup.luanasavian.mercadolivre.request.CompraFormRequest;
import br.com.zup.luanasavian.mercadolivre.request.PagseguroFormRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
    @OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
    private Set<Transacao> transacoes = new HashSet<>();

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

    public void adicionaTransacao(@Valid RetornoGatewayPagamento form) {
        Transacao novaTransacao = form.toTransacao(this);

        Assert.isTrue(!this.transacoes.contains(novaTransacao), "Essa transação já existe!" + novaTransacao);

        Set<Transacao> transacoesConcluidasComSucesso = this.transacoes.stream()
                .filter(Transacao :: concluidaComSucesso).collect(Collectors.toSet());

        Assert.isTrue(transacoesConcluidasComSucesso.isEmpty(), "A compra já foi concluída com sucesso!");

        this.transacoes.add(form.toTransacao(this));
    }

    public boolean concluida() {
        Set<Transacao> transacoesConcluidasComSucesso = this.transacoes.stream()
                .filter(Transacao :: concluidaComSucesso).collect(Collectors.toSet());
        return !transacoesConcluidasComSucesso.isEmpty();
    }

    public URI link() {
        if(gateway.equals(GatewayPagamento.PAGSEGURO)) {
          return UriComponentsBuilder.fromUriString("/retorno-pagseguro/{id}").buildAndExpand(this.getId()).toUri();

        } return UriComponentsBuilder.fromUriString("/retorno-paypal/{id}").buildAndExpand(this.getId()).toUri();
    }
}
