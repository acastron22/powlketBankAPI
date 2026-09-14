package br.com.powlketbank.features.usuario.controller;


import br.com.powlketbank.features.usuario.DTO.request.UsuarioCadastroDTO;
import br.com.powlketbank.features.usuario.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/novo")
    public ResponseEntity<String> novoUsuario(@RequestBody UsuarioCadastroDTO usuario) {

        System.out.println(usuario);

        usuarioService.cadastrarNovoUsuario(usuario);

        return ResponseEntity.ok("Usuário cadastrado com sucesso!");
    }

}
