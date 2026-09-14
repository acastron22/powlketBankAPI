package br.com.powlketbank.features.usuario.services;


import br.com.powlketbank.features.usuario.DTO.request.UsuarioCadastroDTO;
import br.com.powlketbank.features.usuario.domain.Usuario;
import br.com.powlketbank.features.usuario.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario cadastrarNovoUsuario( UsuarioCadastroDTO usuario) {

        Usuario usuarioNovo = new Usuario();

        usuarioRepository.save(usuarioNovo);

        return usuarioNovo;
    }
}
