package br.com.powlketbank.features.usuario.endereco.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco
{

    @Column(nullable = false, length = 128)
    private String logradouro;

    @Column(nullable = false, length = 128)
    private String bairro;

    @Column(nullable = false, length = 8)
    private String cep;

    @Column(length = 16)
    private String numero;

    @Column(length = 128)
    private String complemento;

    @Column(nullable = false, length = 64)
    private String cidade;

    @Column(nullable = false, length = 2)
    private String uf;
}
