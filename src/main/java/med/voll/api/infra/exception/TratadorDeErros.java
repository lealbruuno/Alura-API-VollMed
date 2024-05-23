package med.voll.api.infra.exception; // Pacote que contém a classe TratadorDeErros

import jakarta.persistence.EntityNotFoundException; // Importando a exceção EntityNotFoundException do JPA
import med.voll.api.domain.ValidacaoException; // Importando a exceção ValidacaoException
import org.springframework.http.ResponseEntity; // Importando a classe ResponseEntity do Spring
import org.springframework.validation.FieldError; // Importando a classe FieldError do Spring
import org.springframework.web.bind.MethodArgumentNotValidException; // Importando a exceção MethodArgumentNotValidException do Spring
import org.springframework.web.bind.annotation.ExceptionHandler; // Importando a anotação @ExceptionHandler do Spring
import org.springframework.web.bind.annotation.RestControllerAdvice; // Importando a anotação @RestControllerAdvice do Spring

/**
 * Classe que trata as exceções lançadas pela aplicação.
 */
@RestControllerAdvice
public class TratadorDeErros {

    /**
     * Método que trata a exceção EntityNotFoundException e retorna um ResponseEntity com status 404 Not Found.
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404() {
        return ResponseEntity.notFound().build();
    }

    /**
     * Método que trata a exceção MethodArgumentNotValidException e retorna um ResponseEntity com status 400 Bad Request e uma lista de erros de validação.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
    }

    /**
     * Método que trata a exceção ValidacaoException e retorna um ResponseEntity com status 400 Bad Request e uma mensagem de erro.
     */
    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity tratarErroRegraDeNegocio(ValidacaoException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    /**
     * Classe interna que representa os dados de um erro de validação.
     */
    private record DadosErroValidacao(String campo, String mensagem) {

        /**
         * Construtor que recebe um FieldError e atribui os valores aos campos da classe.
         */
        public DadosErroValidacao(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }

}