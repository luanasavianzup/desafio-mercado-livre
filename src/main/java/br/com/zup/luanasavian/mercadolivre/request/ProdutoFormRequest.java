package br.com.zup.luanasavian.mercadolivre.request;

import br.com.zup.luanasavian.mercadolivre.model.Caracteristica;
import br.com.zup.luanasavian.mercadolivre.model.Categoria;
import br.com.zup.luanasavian.mercadolivre.model.Produto;
import br.com.zup.luanasavian.mercadolivre.model.Usuario;
import br.com.zup.luanasavian.mercadolivre.validation.ExistsId;
import br.com.zup.luanasavian.mercadolivre.validation.UniqueValue;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProdutoFormRequest {

    @NotBlank
    private String nome;
    @NotNull
    @Positive
    private BigDecimal valor;
    @NotNull
    @PositiveOrZero
    private int quantidade;
    @NotBlank
    @Size(max = 1000)
    private String descricao;
    @NotNull
    @ExistsId(domainClass = Categoria.class, fieldName = "id")
    private Long categoriaId;
    @Size(min = 3)
    @Valid
    private List<CaracteristicaFormRequest> caracteristicas = new ArrayList<>();

    @Deprecated
    private ProdutoFormRequest() {}

    public ProdutoFormRequest(@NotBlank String nome, @NotNull @Positive BigDecimal valor, @NotNull @PositiveOrZero int quantidade, @NotBlank @Size(max = 1000) String descricao, @NotNull Long categoriaId, List<CaracteristicaFormRequest> caracteristicas) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.categoriaId = categoriaId;
        this.caracteristicas.addAll(caracteristicas);
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public List<CaracteristicaFormRequest> getCaracteristicas() {
        return caracteristicas;
    }

    public Produto toModel(EntityManager manager, Usuario dono) {
        Categoria categoria = manager.find(Categoria.class, categoriaId);

        return new Produto(nome, valor, quantidade, descricao, categoria, dono, caracteristicas);
    }

    public Set<String> buscaCaracteristicasIguais() {
        HashSet<String> nomesIguais = new HashSet<>();
        HashSet<String> resultados = new HashSet<>();
        for (CaracteristicaFormRequest caracteristica : caracteristicas) {
            String nome = caracteristica.getNome();
            if(!nomesIguais.add(nome)) {
                resultados.add(nome);
            }
        }
        return resultados;
    }
}
