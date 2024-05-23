package med.voll.api.domain.consulta; // Pacote que contém a classe de registro DadosCancelamentoConsulta

import jakarta.validation.constraints.NotNull; // Importando a anotação @NotNull para validar se um campo não é nulo

/**
 * Classe de registro que representa os dados de cancelamento de uma consulta.
 */
public record DadosCancelamentoConsulta(
        
        /**
         * Id da consulta. Não pode ser nulo.
         */
        @NotNull
        Long idConsulta,
        
        /**
         * Motivo do cancelamento. Não pode ser nulo.
         */
        @NotNull
        MotivoCancelamento motivo) {
}