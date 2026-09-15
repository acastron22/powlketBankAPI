package br.com.powlketbank.features.auth.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseCookie;

@Getter
@AllArgsConstructor
public class TokenResponseDTO {
    private final ResponseCookie accessTokenCookie;
    private final ResponseCookie refreshTokenCookie;
    private String tokenDadosUsuario;
}