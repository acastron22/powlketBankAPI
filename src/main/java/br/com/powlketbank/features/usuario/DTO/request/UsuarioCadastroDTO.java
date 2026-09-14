package br.com.powlketbank.features.usuario.DTO.request;

import br.com.powlketbank.features.usuario.endereco.DTO.request.EnderecoCadastroDTO;
import jakarta.validation.constraints.NotBlank;

public record UsuarioCadastroDTO(
        @NotBlank
        String usuario,

        @NotBlank
        String senha,

        @NotBlank
        String nomeCompleto,

        @NotBlank
        String email,

        EnderecoCadastroDTO endereco) {
}
