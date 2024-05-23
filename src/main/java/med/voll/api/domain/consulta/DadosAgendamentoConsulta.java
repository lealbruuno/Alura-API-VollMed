package med.voll.api.domain.consulta; // Pacote que contém a classe de registro DadosAgendamentoConsulta

import jakarta.validation.constraints.Future; // Importando a anotação @Future para validar se um campo é uma data futura
import jakarta.validation.constraints.NotNull; // Importando a anotação @NotNull para validar se um campo não é nulo
import med.voll.api.domain.medico.Especialidade; // Importando a classe Especialidade do pacote med.voll.api.domain.medico

import java.time.LocalDateTime; // Importando a classe LocalDateTime do pacote java.time

/**
 * Classe de registro que representa os dados de agendamento de uma consulta.
 */
public record DadosAgendamentoConsulta(
        
        Long idMedico, // Id do médico
        
        /**
         * Id do paciente. Não pode ser nulo.
         */
        @NotNull
        Long idPaciente,
        
        /**
         * Data da consulta. Não pode ser nula e deve ser uma data futura.
         */
        @NotNull
        @Future
        LocalDateTime data,
        
        Especialidade especialidade // Especialidade do médico
) {
}