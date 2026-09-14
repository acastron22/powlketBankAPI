package br.com.powlketbank.features.usuario.repository;

import br.com.powlketbank.features.usuario.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByUsuario(String usuario);
    boolean existsByEmail(String email);
}
