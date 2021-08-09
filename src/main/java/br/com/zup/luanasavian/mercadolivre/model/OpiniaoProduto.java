package br.com.zup.luanasavian.mercadolivre.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Objects;

@Entity
@Table(name = "tb_opinioes")
public class OpiniaoProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Min(1)
    @Max(5)
    private int nota;
    @NotBlank
    private String titulo;
    @NotBlank
    @Size(max = 500)
    private String descricao;
    @NotNull
    @Valid
    @ManyToOne
    private Usuario usuario;
    @NotNull
    @Valid
    @ManyToOne
    private Produto produto;

    @Deprecated
    private OpiniaoProduto() {}

    public OpiniaoProduto(@NotNull @Min(1) @Max(5) int nota, @NotBlank String titulo, @NotBlank @Size(max = 500) String descricao,
                          @NotNull @Valid Produto produto, @NotNull @Valid Usuario usuario) {

        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.produto = produto;
        this.usuario = usuario;
    }

    public int getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OpiniaoProduto that = (OpiniaoProduto) o;
        return nota == that.nota && Objects.equals(titulo, that.titulo) && Objects.equals(descricao, that.descricao) && Objects.equals(usuario, that.usuario) && Objects.equals(produto, that.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nota, titulo, descricao, usuario, produto);
    }
}
