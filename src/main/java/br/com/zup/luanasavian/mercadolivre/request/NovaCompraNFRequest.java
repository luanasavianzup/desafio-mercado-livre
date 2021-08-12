package br.com.zup.luanasavian.mercadolivre.request;

import br.com.zup.luanasavian.mercadolivre.model.NovaCompraNF;
import br.com.zup.luanasavian.mercadolivre.model.Pagseguro;

import javax.validation.constraints.NotNull;

public class NovaCompraNFRequest {
    @NotNull
    private Long idCompra;
    @NotNull
    private Long idComprador;

    public NovaCompraNFRequest(Long idCompra, Long idComprador) {
        this.idCompra = idCompra;
        this.idComprador = idComprador;
    }

    public NovaCompraNF toModel() {

        return new NovaCompraNF(this.idCompra, this.idComprador);
    }

    public Long getIdCompra() {
        return idCompra;
    }

    public Long getIdComprador() {
        return idComprador;
    }

}
