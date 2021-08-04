package br.com.zup.luanasavian.mercadolivre.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

@Entity
@Table(name = "tb_categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @ManyToOne
    private Categoria categoriaMae;

    public Categoria(String nome) {
        this.nome = nome;
    }

    @Deprecated
    private Categoria() {}

    public Long getId() {
        return id;
    }

    public Categoria getCategoriaMae() {
        return categoriaMae;
    }

    public void setCategoriaMae(Optional<Categoria> categoriaMae) {
        this.categoriaMae = categoriaMae.get();
    }
}
