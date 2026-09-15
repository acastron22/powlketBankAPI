package br.com.powlketbank.infra.security;


import br.com.powlketbank.features.usuario.domain.Usuario;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secret;

    private static final String ISSUER = "PowlketBank";
    private static final String CLAIM_TYPE = "type";
    private static final String TYPE_ACCESS = "ACCESS";
    private static final String TYPE_REFRESH = "REFRESH";
    private static final String TYPE_INFO = "INFO";

    public String gerarTokenAutorizacao(Usuario usuario) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return com.auth0.jwt.JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(usuario.getEmail())
                    .withClaim(CLAIM_TYPE, TYPE_ACCESS)
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritmo);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token de autorização", exception);
        }
    }

    public String gerarRefreshToken(Usuario usuario) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return com.auth0.jwt.JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(usuario.getEmail())
                    .withClaim(CLAIM_TYPE, TYPE_REFRESH)
                    .withExpiresAt(dataExpiracaoRefreshToken())
                    .sign(algoritmo);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar refresh token", exception);
        }
    }

    public String gerarPerfilToken(Usuario usuario) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return com.auth0.jwt.JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(usuario.getEmail())
                    .withClaim(CLAIM_TYPE, TYPE_INFO)
                    .withClaim("nome", usuario.getNomeCompleto())
                    .withExpiresAt(dataExpiracaoRefreshToken())
                    .sign(algoritmo);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token de perfil", exception);
        }
    }

    public String getSubject(String tokenJWT) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            var decodedJWT = com.auth0.jwt.JWT.require(algoritmo)
                    .withIssuer(ISSUER)
                    .withClaim(CLAIM_TYPE, TYPE_ACCESS)
                    .build()
                    .verify(tokenJWT);

            return decodedJWT.getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token de acesso inválido ou expirado", exception);
        }
    }

    public String getSubjectRefreshToken(String refreshToken) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            var decodedJWT = com.auth0.jwt.JWT.require(algoritmo)
                    .withIssuer(ISSUER)
                    .withClaim(CLAIM_TYPE, TYPE_REFRESH)
                    .build()
                    .verify(refreshToken);

            return decodedJWT.getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Refresh token inválido ou expirado", exception);
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(java.time.ZoneOffset.of("-03:00"));
    }

    private Instant dataExpiracaoRefreshToken() {
        return LocalDateTime.now().plusDays(7).toInstant(java.time.ZoneOffset.of("-03:00"));
    }
}
