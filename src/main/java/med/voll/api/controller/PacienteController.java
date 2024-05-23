package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

// Esta classe é um controlador REST para lidar com requisições relacionadas a pacientes
@RestController
// O caminho base para todas as requisições neste controlador é "/pacientes"
@RequestMapping("pacientes")
// Este controlador requer autenticação via token bearer
@SecurityRequirement(name = "bearer-key")
public class PacienteController {

    // Este campo é injetado pelo Spring e representa o repositório de pacientes
    @Autowired
    private PacienteRepository repository;

    // Este método lida com a criação de um novo paciente
    @PostMapping
    @Transactional
    // O corpo da requisição deve conter uma instância válida de
    // DadosCadastroPaciente
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroPaciente dados, UriComponentsBuilder uriBuilder) {
        // Cria uma nova instância de paciente com os dados fornecidos
        var paciente = new Paciente(dados);
        // Salva o novo paciente no banco de dados
        repository.save(paciente);

        // Constrói uma URI para o novo paciente
        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        // Retorna a URI e os dados detalhados do paciente como a resposta
        return ResponseEntity.created(uri).body(new DadosDetalhamentoPaciente(paciente));
    }

    // Este método lida com a recuperação de uma lista de pacientes
    @GetMapping
    public ResponseEntity<Page<DadosListagemPaciente>> listar(
            @PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {
        // Recupera uma página de pacientes do banco de dados, ordenada por nome
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemPaciente::new);
        // Retorna a página de pacientes como a resposta
        return ResponseEntity.ok(page);
    }

    // Este método lida com a atualização das informações de um paciente
    @PutMapping
    @Transactional
    // O corpo da requisição deve conter uma instância válida de
    // DadosAtualizacaoPaciente
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoPaciente dados) {
        // Recupera o paciente a ser atualizado do banco de dados
        var paciente = repository.getReferenceById(dados.id());
        // Atualiza as informações do paciente com os dados fornecidos
        paciente.atualizarInformacoes(dados);

        // Retorna os dados atualizados do paciente como a resposta
        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }

    // Este método lida com a exclusão de um paciente
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        // Recupera o paciente a ser excluído do banco de dados
        var paciente = repository.getReferenceById(id);
        // Exclui o paciente do banco de dados
        paciente.excluir();

        // Retorna uma resposta vazia como confirmação da exclusão
        return ResponseEntity.noContent().build();
    }

    // Este método lida com a recuperação de informações detalhadas de um paciente
    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        // Recupera o paciente com o ID fornecido do banco de dados
        var paciente = repository.getReferenceById(id);

        // Retorna os dados detalhados do paciente como a resposta
        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }
}