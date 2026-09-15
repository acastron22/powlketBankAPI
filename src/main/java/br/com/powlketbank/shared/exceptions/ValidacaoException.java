package br.com.powlketbank.shared.exceptions;

import br.com.powlketbank.shared.DTO.Erro.ErroDTO;
import lombok.Getter;

import java.util.List;

@Getter
public class ValidacaoException extends RuntimeException {
    private final List<ErroDTO> erros;

    public ValidacaoException(List<ErroDTO> erros) {
        super("Falha na validação de cadastro");
        this.erros = erros;
    }
}