package br.com.powlketbank.features.usuario.endereco.DTO.request;

import jakarta.validation.constraints.NotBlank;

public record EnderecoCadastroDTO(

        @NotBlank
        String logradouro,

        @NotBlank
        String bairro,

        @NotBlank
        String cep,

        @NotBlank
        String cidade,
        @NotBlank
        String uf,
        String complemento,
        String numero) {
}
