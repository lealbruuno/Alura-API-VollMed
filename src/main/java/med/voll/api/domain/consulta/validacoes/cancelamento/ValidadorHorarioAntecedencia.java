package med.voll.api.domain.consulta.validacoes.cancelamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidadorHorarioAntecedenciaCancelamento")
public class ValidadorHorarioAntecedencia implements ValidadorCancelamentoDeConsulta {

    @Autowired
    private ConsultaRepository repository;

    // Método para validar se o cancelamento está sendo feito com antecedência mínima de 24h antes da consulta
    public void validar(DadosCancelamentoConsulta dados) {
        // Obtém a instância da consulta através do repositório
        var consulta = repository.getReferenceById(dados.idConsulta());
        
        // Obtém a data e hora atual
        var agora = LocalDateTime.now();
        
        // Calcula a diferença entre a data da consulta e a data atual em horas
        var diferencaEmHoras = Duration.between(agora, consulta.getData()).toHours();

        // Verifica se a diferença é menor que 24h
        if (diferencaEmHoras < 24) {
            throw new ValidacaoException("Consulta somente pode ser cancelada com antecedência mínima de 24h!");
        }
    }
}