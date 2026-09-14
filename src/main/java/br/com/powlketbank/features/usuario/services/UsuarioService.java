package br.com.powlketbank.features.usuario.services;


import br.com.powlketbank.features.usuario.DTO.request.UsuarioCadastroDTO;
import br.com.powlketbank.features.usuario.domain.Usuario;
import br.com.powlketbank.features.usuario.endereco.domain.Endereco;
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
        usuarioNovo.setUsuario(usuario.usuario());
        usuarioNovo.setSenha(usuario.senha()); // lembrar de passar a criptografia
        usuarioNovo.setEmail(usuario.email());
        usuarioNovo.setNomeCompleto(usuario.nomeCompleto());

        if (usuario.endereco() != null) {
            Endereco endereco = new Endereco(
                    usuario.endereco().logradouro(),
                    usuario.endereco().bairro(),
                    usuario.endereco().cep(),
                    usuario.endereco().numero(),
                    usuario.endereco().complemento(),
                    usuario.endereco().cidade(),
                    usuario.endereco().uf()
            );
            usuarioNovo.setEndereco(endereco);
        }
        usuarioRepository.save(usuarioNovo);
        return usuarioNovo;
    }
}
