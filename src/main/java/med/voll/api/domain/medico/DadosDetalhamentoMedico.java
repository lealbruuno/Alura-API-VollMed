package med.voll.api.domain.medico; // Pacote que contém a classe de registro DadosDetalhamentoMedico

import med.voll.api.domain.endereco.Endereco; // Importando a classe Endereco

/**
 * Classe de registro que representa os dados detalhados de um médico.
 */
public record DadosDetalhamentoMedico(
        
        Long id, // Id do médico        
        String nome, // Nome do médico       
        String email, // Email do médico        
        String crm, // CRM do médico        
        String telefone, // Telefone do médico        
        Especialidade especialidade, // Especialidade do médico        
        Endereco endereco // Endereço do médico
) {

        /**
         * Construtor que recebe um médico e atribui os valores aos campos da classe de registro.
         */
        public DadosDetalhamentoMedico(Medico medico) {
                this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(), medico.getEspecialidade(), medico.getEndereco());
        }
}