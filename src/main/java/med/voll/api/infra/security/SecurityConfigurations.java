package med.voll.api.infra.security; // Pacote que contém a classe SecurityConfigurations

import org.springframework.beans.factory.annotation.Autowired; // Importando a anotação de injeção de dependência
import org.springframework.context.annotation.Bean; // Importando a anotação @Bean
import org.springframework.context.annotation.Configuration; // Importando a anotação @Configuration
import org.springframework.http.HttpMethod; // Importando a classe HttpMethod do Spring
import org.springframework.security.authentication.AuthenticationManager; // Importando a interface AuthenticationManager do Spring Security
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration; // Importando a classe AuthenticationConfiguration do Spring Security
import org.springframework.security.config.annotation.web.builders.HttpSecurity; // Importando a classe HttpSecurity do Spring Security
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity; // Importando a anotação @EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy; // Importando a classe SessionCreationPolicy do Spring Security
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; // Importando a classe BCryptPasswordEncoder do Spring Security
import org.springframework.security.crypto.password.PasswordEncoder; // Importando a interface PasswordEncoder do Spring Security
import org.springframework.security.web.SecurityFilterChain; // Importando a interface SecurityFilterChain do Spring Security
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter; // Importando a classe UsernamePasswordAuthenticationFilter do Spring Security

/**
 * Classe que configura a segurança da aplicação.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    private SecurityFilter securityFilter; // Filtro de segurança injetado pela Spring

    /**
     * Método que configura a segurança da aplicação.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable() // Desabilita o CSRF
               .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Define a política de criação de sessão como STATELESS
               .and().authorizeHttpRequests() // Define as regras de autorização para as requisições
               .requestMatchers(HttpMethod.POST, "/login").permitAll() // Permite o acesso ao endpoint de login
               .requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll() // Permite o acesso à documentação da API
               .anyRequest().authenticated() // Exige autenticação para todas as demais requisições
               .and().addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class) // Adiciona o filtro de segurança antes do filtro de autenticação de usuário e senha
               .build();
    }

    /**
     * Método que retorna o AuthenticationManager do Spring Security.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    /**
     * Método que retorna o PasswordEncoder do Spring Security.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}