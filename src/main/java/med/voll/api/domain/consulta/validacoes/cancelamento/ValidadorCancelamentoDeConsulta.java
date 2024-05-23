package med.voll.api.domain.consulta.validacoes.cancelamento;

import med.voll.api.domain.consulta.DadosCancelamentoConsulta;

// Interface para validação do cancelamento de uma consulta
public interface ValidadorCancelamentoDeConsulta {

    // Método para validar o cancelamento de uma consulta
    void validar(DadosCancelamentoConsulta dados);

}