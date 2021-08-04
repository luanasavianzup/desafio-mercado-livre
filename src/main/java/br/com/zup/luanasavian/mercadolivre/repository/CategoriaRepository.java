package br.com.zup.luanasavian.mercadolivre.repository;

import br.com.zup.luanasavian.mercadolivre.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    Optional<Categoria> findByNome(String categoriaMaeNome);
}
