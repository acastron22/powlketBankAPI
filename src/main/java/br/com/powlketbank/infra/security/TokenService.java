package br.com.powlketbank.infra.security;


import br.com.powlketbank.features.usuario.domain.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

import static org.springframework.security.config.Elements.JWT;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secret;

    public String gerarTOkenAutorizacao( Usuario usuario) {
        try {

            var algoritimo = Algorithm.HMAC256(secret);


            return com.auth0.jwt.JWT.create()
                    .withIssuer("PowlketBank")
                    .withSubject(usuario.getUsuario())
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritimo);


        }catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token de autorização", exception);
        }
    }

    public String getSUbject(String tokenJWT){
        try{
            var algoritmo = Algorithm.HMAC256(secret);

            return com.auth0.jwt.JWT.require(algoritmo)
                    .withIssuer("PowlketBank")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        }
        catch (JWTVerificationException exception){

            throw new RuntimeException("Erro ao validar autorização", exception);

        }
    }

    private Instant dataExpiracao(){
        return LocalDateTime.now().plusHours(2).toInstant(java.time.ZoneOffset.of("-03:00"));
    }
}
