package br.com.powlketbank.features.auth.service;


import br.com.powlketbank.features.auth.DTO.request.LoginRequestDTO;
import br.com.powlketbank.features.auth.DTO.response.TokenResponseDTO;
import br.com.powlketbank.features.usuario.domain.Usuario;
import br.com.powlketbank.features.usuario.repository.UsuarioRepository;
import br.com.powlketbank.infra.security.TokenService;
import br.com.powlketbank.shared.DTO.Erro.ErroDTO;
import br.com.powlketbank.shared.exceptions.ValidacaoException;
import br.com.powlketbank.shared.services.CookieService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final CookieService cookieService;

    public Usuario autenticar(LoginRequestDTO dados){

        Usuario usuario = usuarioRepository.findByEmail(dados.email())
                .orElseThrow(() -> new ValidacaoException(List.of(new ErroDTO("Login", 401, "Usuário ou senha inválidos"))));

        if (!usuario.isAtivo()) {
            throw new ValidacaoException(List.of(new ErroDTO("UsuarioInativo", 401, "Usuário desativado, verifique com a equipe")));
        }


        if (!passwordEncoder.matches(dados.senha(), usuario.getSenha())) {
            throw new ValidacaoException(List.of(new ErroDTO("Login", 401, "Usuário ou senha inválidos")));
        }

        return usuario;
    }



    private TokenResponseDTO gerarTokenResponse(Usuario usuario){

        String accessToken = tokenService.gerarTokenAutorizacao(usuario);
        String refreshToken = tokenService.gerarRefreshToken(usuario);
        String tokenDadosUsuario = tokenService.gerarPerfilToken(usuario);

        var accessTokenCookie = cookieService.criarCookie("accessToken", accessToken, 10 * 3600);
        var refreshTokenCookie = cookieService.criarCookie("refreshToken", refreshToken, 72 * 3600);


        return new TokenResponseDTO(accessTokenCookie, refreshTokenCookie, tokenDadosUsuario);
    }

}
