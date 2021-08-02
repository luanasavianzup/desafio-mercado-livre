package br.com.zup.luanasavian.mercadolivre.repository;

import br.com.zup.luanasavian.mercadolivre.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
