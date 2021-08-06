package br.com.zup.luanasavian.mercadolivre.request;

import br.com.zup.luanasavian.mercadolivre.model.OpiniaoProduto;
import br.com.zup.luanasavian.mercadolivre.model.Produto;
import br.com.zup.luanasavian.mercadolivre.model.Usuario;

import javax.validation.Valid;
import javax.validation.constraints.*;

public class OpiniaoProdutoFormRequest {

    @NotNull
    @Min(1)
    @Max(5)
    private int nota;
    @NotBlank
    private String titulo;
    @NotBlank
    @Size(max = 500)
    private String descricao;

    public OpiniaoProdutoFormRequest(@NotNull @Min(1) @Max(5) int nota, @NotBlank String titulo, @NotBlank @Size(max = 500) String descricao) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public OpiniaoProduto toModel(@NotNull @Valid Produto produto, @NotNull @Valid Usuario consumidor) {
        return new OpiniaoProduto(this.nota, this.titulo, this.descricao, produto, consumidor);
    }
}
