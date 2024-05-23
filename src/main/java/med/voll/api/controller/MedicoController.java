package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

// Indica que esta classe é um controlador REST
@RestController

// Define o caminho base para as requisições deste controlador
@RequestMapping("medicos")

// Indica que esta classe requer autenticação via token bearer
@SecurityRequirement(name = "bearer-key")
public class MedicoController {

    // Injeta o repositório de médicos no controlador
    @Autowired
    private MedicoRepository repository;

    // Define o método para cadastrar um novo médico
    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder) {

        // Cria um novo objeto médico com os dados recebidos
        var medico = new Medico(dados);

        // Salva o novo médico no banco de dados
        repository.save(medico);

        // Cria uma URI para o novo médico
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        // Retorna a URI e os dados do novo médico como resposta da requisição
        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }

    // Define o método para listar todos os médicos ativos
    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> listar(
            @PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {

        // Busca todos os médicos ativos no banco de dados, paginados de acordo com os
        // parâmetros recebidos
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);

        // Retorna a lista de médicos como resposta da requisição
        return ResponseEntity.ok(page);
    }

    // Define o método para atualizar os dados de um médico
    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {

        // Busca o médico a ser atualizado no banco de dados
        var medico = repository.getReferenceById(dados.id());

        // Atualiza as informações do médico com os dados recebidos
        medico.atualizarInformacoes(dados);

        // Retorna os dados atualizados do médico como resposta da requisição
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    // Define o método para excluir um médico
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {

        // Busca o médico a ser excluído no banco de dados
        var medico = repository.getReferenceById(id);

        // Exclui o médico do banco de dados
        medico.excluir();

        // Retorna uma resposta vazia como confirmação da exclusão
        return ResponseEntity.noContent().build();
    }

    // Define o método para detalhar um médico
    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {

        // Busca o médico a ser detalhado no banco de dados
        var medico = repository.getReferenceById(id);

        // Retorna os dados do médico como resposta da requisição
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }
}