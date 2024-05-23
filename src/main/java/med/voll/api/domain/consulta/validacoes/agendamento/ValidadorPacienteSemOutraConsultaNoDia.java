package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteSemOutraConsultaNoDia implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private ConsultaRepository repository;

    // Método para validar se o paciente não possui outra consulta agendada no mesmo dia
    public void validar(DadosAgendamentoConsulta dados) {
        // Define o primeiro horário do dia (7h)
        var primeiroHorario = dados.data().withHour(7);
        
        // Define o último horário do dia (18h)
        var ultimoHorario = dados.data().withHour(18);
        
        // Verifica se o paciente possui outra consulta agendada no mesmo dia
        var pacientePossuiOutraConsultaNoDia = repository.existsByPacienteIdAndDataBetween(dados.idPaciente(), primeiroHorario, ultimoHorario);

        // Se o paciente já possui uma consulta agendada nesse dia, lança uma exceção de validação
        if (pacientePossuiOutraConsultaNoDia) {
            throw new ValidacaoException("Paciente já possui uma consulta agendada nesse dia");
        }
    }

}