package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;

// Interface responsável por validar o agendamento de uma consulta
public interface ValidadorAgendamentoDeConsulta {

    // Método para validar os dados do agendamento de uma consulta
    void validar(DadosAgendamentoConsulta dados);

}