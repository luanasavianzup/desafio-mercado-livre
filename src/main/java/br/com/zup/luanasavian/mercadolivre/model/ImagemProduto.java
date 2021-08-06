package br.com.zup.luanasavian.mercadolivre.model;

import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "tb_imagem")
public class ImagemProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @Valid
    @NotNull
    private Produto produto;
    @URL
    @NotBlank
    private String link;

    @Deprecated
    public ImagemProduto() {
    }

    public ImagemProduto(@NotNull @Valid Produto produto, @URL @NotBlank String link) {
        this.produto = produto;
        this.link = link;
    }

    public Long getId() {
        return id;
    }

    public String getLink() {
        return link;
    }

    public Produto getProduto() {
        return produto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImagemProduto that = (ImagemProduto) o;
        return produto.equals(that.produto) && link.equals(that.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(produto, link);
    }
}
