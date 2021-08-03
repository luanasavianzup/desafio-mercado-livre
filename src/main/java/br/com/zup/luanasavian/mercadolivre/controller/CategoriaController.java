package br.com.zup.luanasavian.mercadolivre.controller;

import br.com.zup.luanasavian.mercadolivre.model.Categoria;
import br.com.zup.luanasavian.mercadolivre.repository.CategoriaRepository;
import br.com.zup.luanasavian.mercadolivre.request.CategoriaFormRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    public void post(@RequestBody @Valid CategoriaFormRequest form, UriComponentsBuilder uriBuilder) {
        Categoria categoria = form.toModel(manager);
        categoriaRepository.save(categoria);

        URI uri = uriBuilder.path("/categorias/{id}").buildAndExpand(categoria.getId()).toUri();
    }
}
