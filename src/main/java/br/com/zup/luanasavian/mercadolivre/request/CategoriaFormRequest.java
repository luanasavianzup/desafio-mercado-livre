package br.com.zup.luanasavian.mercadolivre.request;

import br.com.zup.luanasavian.mercadolivre.model.Categoria;
import br.com.zup.luanasavian.mercadolivre.repository.CategoriaRepository;
import br.com.zup.luanasavian.mercadolivre.validation.ExistsId;
import br.com.zup.luanasavian.mercadolivre.validation.UniqueValue;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;

public class CategoriaFormRequest {

    @NotBlank
    @UniqueValue(domainClass = Categoria.class, fieldName = "nome")
    private String nome;
    @ExistsId(domainClass = Categoria.class, fieldName = "id")
    private Long categoriaMaeId;

    public CategoriaFormRequest(@NotBlank String nome, Long categoriaMaeId) {
        this.nome = nome;
        this.categoriaMaeId = categoriaMaeId;
    }

    public String getNome() {
        return nome;
    }

    public Long getCategoriaMaeId() {
        return categoriaMaeId;
    }

    public Categoria toModel(EntityManager manager) {
        Categoria categoria = new Categoria(nome);
        if (categoriaMaeId != null) {
            Categoria categoriaMae = manager.find(Categoria.class, categoriaMaeId);
            categoria.setCategoriaMae(categoriaMae);
        }
        return categoria;
    }
}
