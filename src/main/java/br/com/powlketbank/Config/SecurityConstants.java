package br.com.powlketbank.Config;

public class SecurityConstants {

    // Rotas acessíveis sem token (Login e Cadastro inicial)
    public static final String[] ROTAS_PUBLICAS = {
            "/auth/login/**",
            "/usuarios/novo",
            "/auth/refresh-token/**",
            "/version/**",
            "/error"
    };

    // Rotas da documentação Swagger UI
    public static final String[] ROTAS_SWAGGER = {
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/webjars/**"
    };

    public static final String[] ROTAS_LEITURA_GERAL = {

    };

    public static final String[] RECURSOS_AUTENTICADOS = {

    };

    public static final String[] ORIGENS_PERMITIDAS = {
            "http://localhost:4200",
            "http://127.0.0.1:4200",
            "http://localhost:8085"
    };
}