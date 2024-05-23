package med.voll.api.domain.consulta; // Pacote que contém a classe de registro DadosDetalhamentoConsulta

import java.time.LocalDateTime; // Importando a classe LocalDateTime do pacote java.time

/**
 * Classe de registro que representa os dados detalhados de uma consulta.
 */
public record DadosDetalhamentoConsulta(
        
        Long id, // Id da consulta
        
        Long idMedico, // Id do médico
        
        Long idPaciente, // Id do paciente
        
        LocalDateTime data // Data da consulta
) {
        
        /**
         * Construtor que recebe uma consulta e atribui os valores dos campos da consulta aos campos da classe de registro.
         *
         */
        public DadosDetalhamentoConsulta(Consulta consulta) {
                this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData());
        }
}