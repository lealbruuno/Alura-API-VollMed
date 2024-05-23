package med.voll.api.domain.endereco; // Pacote que contém a classe Endereco

import jakarta.persistence.Embeddable; // Importando a anotação @Embeddable para indicar que a classe será incorporada em outra
import lombok.AllArgsConstructor; // Importando a anotação @AllArgsConstructor para gerar um construtor com todos os campos
import lombok.Getter; // Importando a anotação @Getter para gerar getters para todos os campos
import lombok.NoArgsConstructor; // Importando a anotação @NoArgsConstructor para gerar um construtor sem parâmetros

/**
 * Classe que representa o endereço de um paciente.
 */
@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    /**
     * Logradouro do endereço.
     */
    private String logradouro;
    
    /**
     * Bairro do endereço.
     */
    private String bairro;
    
    /**
     * CEP do endereço.
     */
    private String cep;
    
    /**
     * Número do endereço.
     */
    private String numero;
    
    /**
     * Complemento do endereço.
     */
    private String complemento;
    
    /**
     * Cidade do endereço.
     */
    private String cidade;
    
    /**
     * UF do endereço.
     */
    private String uf;

    /**
     * Construtor que recebe os dados de endereço e atribui os valores aos campos da classe.
     *
     */
    public Endereco(DadosEndereco dados) {
        this.logradouro = dados.logradouro();
        this.bairro = dados.bairro();
        this.cep = dados.cep();
        this.uf = dados.uf();
        this.cidade = dados.cidade();
        this.numero = dados.numero();
        this.complemento = dados.complemento();
    }

    /**
     * Método que atualiza as informações do endereço com os dados fornecidos.
     *
     */
    public void atualizarInformacoes(DadosEndereco dados) {
        if (dados.logradouro()!= null) {
            this.logradouro = dados.logradouro();
        }
        if (dados.bairro()!= null) {
            this.bairro = dados.bairro();
        }
        if (dados.cep()!= null) {
            this.cep = dados.cep();
        }
        if (dados.uf()!= null) {
            this.uf = dados.uf();
        }
        if (dados.cidade()!= null) {
            this.cidade = dados.cidade();
        }
        if (dados.numero()!= null) {
            this.numero = dados.numero();
        }
        if (dados.complemento()!= null) {
            this.complemento = dados.complemento();
        }
    }
}