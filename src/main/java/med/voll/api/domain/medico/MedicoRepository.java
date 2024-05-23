package med.voll.api.domain.medico; // Pacote que contém a interface MedicoRepository

import org.springframework.data.domain.Page; // Importando a interface Page do Spring Data
import org.springframework.data.domain.Pageable; // Importando a interface Pageable do Spring Data
import org.springframework.data.jpa.repository.JpaRepository; // Importando a interface JpaRepository do Spring Data
import org.springframework.data.jpa.repository.Query; // Importando a anotação @Query do Spring Data

import java.time.LocalDateTime; // Importando a classe LocalDateTime do pacote java.time

/**
 * Interface que define os métodos de acesso ao banco de dados para a entidade Medico.
 */
public interface MedicoRepository extends JpaRepository<Medico, Long> {

    /**
     * Método que retorna uma página de médicos ativos.
     */
    Page<Medico> findAllByAtivoTrue(Pageable paginacao);

    /**
     * Método que retorna um médico aleatório ativo e livre na data especificada.
     */
    @Query("""
            select m from Medico m
            where
            m.ativo = 1
            and
            m.especialidade = :especialidade
            and
            m.id not in(
                select c.medico.id from Consulta c
                where
                c.data = :data
                and
                c.motivoCancelamento is null
            )
            order by rand()
            limit 1
        """)
    Medico escolherMedicoAleatorioLivreNaData(Especialidade especialidade, LocalDateTime data);

    /**
     * Método que retorna o valor do atributo ativo de um médico com o id especificado.
     */
    @Query("""
            select m.ativo
            from Medico m
            where
            m.id = :id
            """)
    Boolean findAtivoById(Long id);
}