package br.com.zup.luanasavian.mercadolivre.request;

import br.com.zup.luanasavian.mercadolivre.model.Caracteristica;

public class DetalheProdutoCaracteristica {

    private String nome;
    private String descricao;

    @Deprecated
    private DetalheProdutoCaracteristica() {}

    public DetalheProdutoCaracteristica(Caracteristica caracteristica) {
        this.descricao = caracteristica.getDescricao();
        this.nome = caracteristica.getNome();
    }

    public String getDescricao() {
        return descricao;
    }

    public String getNome() {
        return nome;
    }
}
