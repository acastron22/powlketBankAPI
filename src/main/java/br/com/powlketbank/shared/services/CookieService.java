package br.com.powlketbank.shared.services;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

@Service
public class CookieService {

    public ResponseCookie criarCookie(String nome, String valor, long maxAge) {

        return ResponseCookie.from(nome, valor)
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(maxAge)
                .sameSite("Lax")
                .build();


    }

    public String extrairCookie(HttpServletRequest request, String nome) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(nome)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
