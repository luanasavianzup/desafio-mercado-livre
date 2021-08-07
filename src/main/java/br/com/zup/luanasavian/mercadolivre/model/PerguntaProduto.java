package br.com.zup.luanasavian.mercadolivre.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tb_perguntas")
public class PerguntaProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String titulo;
    private LocalDateTime instante = LocalDateTime.now();
    @NotNull
    @ManyToOne
    private Usuario consumidor;
    @NotNull
    @ManyToOne
    private Produto produto;

    @Deprecated
    public PerguntaProduto() {
    }

    public PerguntaProduto(@NotBlank String titulo, Produto produto, Usuario consumidor) {
        this.titulo = titulo;
        this.produto = produto;
        this.consumidor = consumidor;
        this.instante = LocalDateTime.now();
    }

    public String getTitulo() {
        return titulo;
    }

    public Usuario getConsumidor() {
        return consumidor;
    }

    public Produto getProduto() {
        return produto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PerguntaProduto that = (PerguntaProduto) o;
        return Objects.equals(titulo, that.titulo) && Objects.equals(consumidor, that.consumidor) && Objects.equals(produto, that.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, produto, consumidor);
    }

    public Usuario getInteressado() {
        return consumidor;
    }

    public Usuario getDonoProduto() {
        return produto.getDono();
    }
}
