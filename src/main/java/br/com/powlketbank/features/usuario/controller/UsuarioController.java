package br.com.powlketbank.features.usuario.controller;


import br.com.powlketbank.features.usuario.DTO.request.UsuarioCadastroDTO;
import br.com.powlketbank.features.usuario.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/novo")
    public ResponseEntity<String> novoUsuario(UsuarioCadastroDTO usuario) {

        System.out.println(usuario);

//        usuarioService.cadastrarNovoUsuario(usuario);

        return ResponseEntity.ok("Usuário cadastrado com sucesso!");
    }

}
