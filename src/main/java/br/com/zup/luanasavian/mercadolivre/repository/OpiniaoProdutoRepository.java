package br.com.zup.luanasavian.mercadolivre.repository;

import br.com.zup.luanasavian.mercadolivre.model.OpiniaoProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpiniaoProdutoRepository extends JpaRepository<OpiniaoProduto, Long> {
}
