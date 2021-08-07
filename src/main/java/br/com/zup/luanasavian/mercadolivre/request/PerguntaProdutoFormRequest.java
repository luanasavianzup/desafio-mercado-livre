package br.com.zup.luanasavian.mercadolivre.request;

import br.com.zup.luanasavian.mercadolivre.model.PerguntaProduto;
import br.com.zup.luanasavian.mercadolivre.model.Produto;
import br.com.zup.luanasavian.mercadolivre.model.Usuario;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class PerguntaProdutoFormRequest {

    @NotBlank
    private String titulo;

    @Deprecated
    private PerguntaProdutoFormRequest() {}

    public PerguntaProdutoFormRequest(@NotBlank String titulo) {
        this.titulo = titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public PerguntaProduto toModel(@NotNull @Valid Produto produto, @NotNull @Valid Usuario consumidor) {
        return new PerguntaProduto(titulo, produto, consumidor);
    }
}
