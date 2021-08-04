package br.com.zup.luanasavian.mercadolivre.request;

import br.com.zup.luanasavian.mercadolivre.model.Categoria;
import br.com.zup.luanasavian.mercadolivre.repository.CategoriaRepository;
import br.com.zup.luanasavian.mercadolivre.validation.ExistsId;
import br.com.zup.luanasavian.mercadolivre.validation.UniqueValue;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

public class CategoriaFormRequest {

    @NotBlank
    @UniqueValue(domainClass = Categoria.class, fieldName = "nome")
    private String nome;
    private String categoriaMaeNome;

    public CategoriaFormRequest(@NotBlank String nome, String categoriaMaeNome) {
        this.nome = nome;
        this.categoriaMaeNome = categoriaMaeNome;
    }

    public String getNome() {
        return nome;
    }

    public String getCategoriaMaeNome() {
        return categoriaMaeNome;
    }

    public Categoria toModel(CategoriaRepository categoriaRepository) {
        Categoria categoria = new Categoria(nome);
        if (categoriaMaeNome != null) {
            Optional<Categoria> categoriaMae = categoriaRepository.findByNome(categoriaMaeNome);
            categoria.setCategoriaMae(categoriaMae);
        }
        return categoria;
    }
}
