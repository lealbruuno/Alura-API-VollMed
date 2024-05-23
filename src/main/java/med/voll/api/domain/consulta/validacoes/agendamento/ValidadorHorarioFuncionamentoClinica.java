package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

// Componente responsável por validar se o horário de agendamento está dentro do horário de funcionamento da clínica
@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoDeConsulta {

    // Método para validar se o horário de agendamento está dentro do horário de funcionamento da clínica
    public void validar(DadosAgendamentoConsulta dados) {
        // Obtém a data da consulta
        var dataConsulta = dados.data();

        // Verifica se a consulta é em um domingo
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        
        // Verifica se o horário da consulta é anterior à abertura da clínica (7h)
        var antesDaAberturaDaClinica = dataConsulta.getHour() < 7;
        
        // Verifica se o horário da consulta é posterior ao encerramento da clínica (18h)
        var depoisDoEncerramentoDaClinica = dataConsulta.getHour() > 18;
        
        // Se a consulta for em um domingo ou antes/after do horário de funcionamento, lança uma exceção de validação
        if (domingo || antesDaAberturaDaClinica || depoisDoEncerramentoDaClinica) {
            throw new ValidacaoException("Consulta fora do horário de funcionamento da clínica");
        }
    }
}