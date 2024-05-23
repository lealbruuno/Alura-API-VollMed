package med.voll.api.domain.consulta; // Pacote que contém a interface ConsultaRepository

import org.springframework.data.jpa.repository.JpaRepository; // Importando a interface JpaRepository do Spring Data JPA

import java.time.LocalDateTime; // Importando a classe LocalDateTime do pacote java.time

/**
 * Interface para o repositório de Consulta.
 */
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    /**
     * Verifica se existe uma consulta para um determinado paciente em um intervalo de tempo.
     *
     */
    boolean existsByPacienteIdAndDataBetween(Long idPaciente, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);

    /**
     * Verifica se existe uma consulta para um determinado médico em um horário específico.
     *
     */
    boolean existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(Long idMedico, LocalDateTime data);
}