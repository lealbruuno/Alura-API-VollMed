package med.voll.api.domain.endereco; // Pacote que contém a classe de registro DadosEndereco

import jakarta.validation.constraints.NotBlank; // Importando a anotação @NotBlank para validar se um campo não é vazio
import jakarta.validation.constraints.Pattern; // Importando a anotação @Pattern para validar se um campo segue um padrão específico

/**
 * Classe de registro que representa os dados de endereço.
 */
public record DadosEndereco(
        
        /**
         * Logradouro do endereço. Não pode ser vazio.
         */
        @NotBlank
        String logradouro,
        
        /**
         * Bairro do endereço. Não pode ser vazio.
         */
        @NotBlank
        String bairro,
        
        /**
         * CEP do endereço. Não pode ser vazio e deve seguir o padrão de 8 dígitos.
         */
        @NotBlank
        @Pattern(regexp = "\\d{8}")
        String cep,
        
        /**
         * Cidade do endereço. Não pode ser vazio.
         */
        @NotBlank
        String cidade,
        
        /**
         * UF do endereço. Não pode ser vazio.
         */
        @NotBlank
        String uf,
        
        /**
         * Complemento do endereço. Pode ser vazio.
         */
        String complemento,
        
        /**
         * Número do endereço. Pode ser vazio.
         */
        String numero) {
}