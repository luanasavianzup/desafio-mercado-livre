package br.com.zup.luanasavian.mercadolivre.request;

import br.com.zup.luanasavian.mercadolivre.model.NovaCompraNF;
import br.com.zup.luanasavian.mercadolivre.model.Ranking;

import javax.validation.constraints.NotNull;

public class RankingNovaCompraRequest {

    @NotNull
    private Long idCompra;
    @NotNull
    private  Long idDonoProduto;

    public RankingNovaCompraRequest(@NotNull Long idCompra, @NotNull Long idDonoProduto) {
        this.idCompra = idCompra;
        this.idDonoProduto = idDonoProduto;
    }

    public Ranking toModel() {

        return new Ranking(this.idCompra, this.idDonoProduto);
    }
}
