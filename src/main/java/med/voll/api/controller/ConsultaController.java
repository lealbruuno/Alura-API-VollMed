// Pacote que contém o controlador da API
package med.voll.api.controller;

// Importação de bibliotecas e classes necessárias
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.AgendaDeConsultas;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

// Define que esta classe é um controlador de API REST
@RestController
// Mapeia o caminho base para todas as rotas deste controlador
@RequestMapping("consultas")
// Define o requisito de segurança para autenticação
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    // Injeta a dependência da classe AgendaDeConsultas
    @Autowired
    private AgendaDeConsultas agenda;

    // Mapeia a rota POST para agendar uma consulta
    @PostMapping
    // Define que a transação é necessária para esse método
    @Transactional
    // Recebe como parâmetro um objeto DadosAgendamentoConsulta validado
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) {
        // Chama o método agendar da dependência agenda e recebe um DTO
        var dto = agenda.agendar(dados);
        // Retorna a resposta com o DTO e o código de status OK (200)
        return ResponseEntity.ok(dto);
    }

    // Mapeia a rota DELETE para cancelar uma consulta
    @DeleteMapping
    // Define que a transação é necessária para esse método
    @Transactional
    // Recebe como parâmetro um objeto DadosCancelamentoConsulta validado
    public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados) {
        // Chama o método cancelar da dependência agenda
        agenda.cancelar(dados);
        // Retorna uma resposta vazia com o código de status No Content (204)
        return ResponseEntity.noContent().build();
    }

}