package med.voll.api.domain.medico; // Pacote que contém a classe Medico

import jakarta.persistence.*; // Importando as anotações e interfaces do JPA
import lombok.AllArgsConstructor; // Importando a anotação @AllArgsConstructor para gerar um construtor com todos os campos
import lombok.EqualsAndHashCode; // Importando a anotação @EqualsAndHashCode para gerar métodos equals() e hashCode()
import lombok.Getter; // Importando a anotação @Getter para gerar getters para todos os campos
import lombok.NoArgsConstructor; // Importando a anotação @NoArgsConstructor para gerar um construtor sem parâmetros
import med.voll.api.domain.endereco.Endereco; // Importando a classe Endereco

/**
 * Classe que representa um médico.
 */
@Table(name = "medicos") // Nome da tabela no banco de dados
@Entity(name = "Medico") // Nome da entidade JPA
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    /**
     * Id do médico.
     */
    @Id // Indica que o campo é a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Indica que o valor será gerado automaticamente pelo banco de dados
    private Long id;
    
    /**
     * Nome do médico.
     */
    private String nome;
    
    /**
     * Email do médico.
     */
    private String email;
    
    /**
     * Telefone do médico.
     */
    private String telefone;
    
    /**
     * CRM do médico.
     */
    private String crm;
    
    /**
     * Especialidade do médico.
     */
    @Enumerated(EnumType.STRING) // Indica que o valor será armazenado como uma string no banco de dados
    private Especialidade especialidade;
    
    /**
     * Endereço do médico.
     */
    @Embedded // Indica que o campo é incorporado na entidade
    private Endereco endereco;
    
    /**
     * Indica se o médico está ativo ou não.
     */
    private Boolean ativo;

    /**
     * Construtor que recebe os dados de cadastro de um médico e atribui os valores aos campos da classe.
     *
     */
    public Medico(DadosCadastroMedico dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
    }

    /**
     * Método que atualiza as informações do médico com os dados fornecidos.
     *
     */
    public void atualizarInformacoes(DadosAtualizacaoMedico dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null) {
            this.endereco.atualizarInformacoes(dados.endereco());
        }
    }

    /**
     * Método que exclui o médico, marcando-o como inativo.
     */
    public void excluir() {
        this.ativo = false;
    }
}