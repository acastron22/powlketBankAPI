package br.com.powlketbank.Config;


import br.com.powlketbank.features.usuario.repository.UsuarioRepository;
import br.com.powlketbank.infra.security.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private final TokenService tokenService;
    @Autowired
    private final UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        var tokenJWT = recuperarTokenAcesso(request);

        if (tokenJWT != null) {
            var subject = tokenService.getSubject(tokenJWT);

            var usuario = usuarioRepository.findByUsuario(subject);

            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }


        filterChain.doFilter(request, response);

    }

    private String recuperarTokenAcesso(HttpServletRequest request) {
        var token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            System.out.println(token);
            return token.replace("Bearer ", "");
        }

        return null;
    }



}
