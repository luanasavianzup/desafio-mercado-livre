package br.com.zup.luanasavian.mercadolivre.request;

import br.com.zup.luanasavian.mercadolivre.model.Caracteristica;
import br.com.zup.luanasavian.mercadolivre.model.Produto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CaracteristicaFormRequest {

    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;

    @Deprecated
    private CaracteristicaFormRequest() {}

    public CaracteristicaFormRequest(@NotBlank String nome, @NotBlank String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Caracteristica toModel(@NotNull @Valid Produto produto) {
        return new Caracteristica(nome, descricao, produto);
    }


}
