package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private MedicoRepository repository;

    // Método para validar se o médico está ativo no momento do agendamento da consulta
    public void validar(DadosAgendamentoConsulta dados) {
        // Verifica se foi escolhido um médico (opcional)
        if (dados.idMedico() == null) {
            // Se não foi escolhido um médico, não há necessidade de validação
            return;
        }

        // Verifica se o médico está ativo
        var medicoEstaAtivo = repository.findAtivoById(dados.idMedico());
        
        // Se o médico não está ativo, lança uma exceção de validação
        if (!medicoEstaAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada com médico excluído");
        }
    }

}