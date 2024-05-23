package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

// Componente responsável por validar se o horário de agendamento está dentro da antecedência mínima
@Component("ValidadorHorarioAntecedenciaAgendamento")
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoDeConsulta {

    // Método para validar se o horário de agendamento está dentro da antecedência mínima de 30 minutos
    public void validar(DadosAgendamentoConsulta dados) {
        // Obtém a data da consulta e o horário atual
        var dataConsulta = dados.data();
        var agora = LocalDateTime.now();
        
        // Calcula a diferença em minutos entre o horário atual e a data da consulta
        var diferencaEmMinutos = Duration.between(agora, dataConsulta).toMinutes();

        // Se a diferença for menor do que 30 minutos, lança uma exceção de validação
        if (diferencaEmMinutos < 30) {
            throw new ValidacaoException("Consulta deve ser agendada com antecedência mínima de 30 minutos");
        }
    }
}