package br.com.zup.luanasavian.mercadolivre.repository;

import br.com.zup.luanasavian.mercadolivre.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
