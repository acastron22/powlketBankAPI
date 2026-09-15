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
public class Endereco {

    @Column(length = 128)
    private String logradouro;

    @Column(length = 128)
    private String bairro;

    @Column(length = 8)
    private String cep;

    @Column(length = 16)
    private String numero;

    @Column(length = 128)
    private String complemento;

    @Column(length = 64)
    private String cidade;

    @Column(length = 2)
    private String uf;
}
