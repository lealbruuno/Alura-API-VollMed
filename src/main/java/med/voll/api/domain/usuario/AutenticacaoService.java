package med.voll.api.domain.usuario; // Pacote que contém a classe AutenticacaoService

import org.springframework.beans.factory.annotation.Autowired; // Importando a anotação de injeção de dependência
import org.springframework.security.core.userdetails.UserDetails; // Importando a interface UserDetails do Spring Security
import org.springframework.security.core.userdetails.UserDetailsService; // Importando a interface UserDetailsService do Spring Security
import org.springframework.security.core.userdetails.UsernameNotFoundException; // Importando a exceção UsernameNotFoundException do Spring Security
import org.springframework.stereotype.Service; // Importando a anotação @Service do Spring

/**
 * Classe que implementa a interface UserDetailsService do Spring Security.
 */
@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository; // Repositório de usuários injetado pela Spring

    /**
     * Método que retorna os detalhes do usuário com o login especificado.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username);
    }
}