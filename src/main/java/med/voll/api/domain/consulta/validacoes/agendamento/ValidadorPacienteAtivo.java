package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private PacienteRepository repository;

    // Método para validar se o paciente está ativo no momento do agendamento da consulta
    public void validar(DadosAgendamentoConsulta dados) {
        // Verifica se o paciente está ativo
        var pacienteEstaAtivo = repository.findAtivoById(dados.idPaciente());
        
        // Se o paciente não está ativo, lança uma exceção de validação
        if (!pacienteEstaAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada com paciente excluído");
        }
    }
}